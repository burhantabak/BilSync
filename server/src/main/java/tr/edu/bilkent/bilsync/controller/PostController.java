package tr.edu.bilkent.bilsync.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.entity.*;
import tr.edu.bilkent.bilsync.entity.PostEntities.*;
import tr.edu.bilkent.bilsync.repository.PostRepositories.VoteRepository;
import tr.edu.bilkent.bilsync.service.PostServices.PostService;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.TreeSet;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final VoteRepository voteRepository;

    /**
     * Constructor for PostController, injecting the required PostService.
     *
     * @param postService The PostService for handling post-related operations.
     */
    public PostController(PostService postService, VoteRepository voteRepository) {
        this.postService = postService;
        this.voteRepository = voteRepository;
    }

    /**
     * Creates a new post based on the provided JSON node.
     *
     * @param postNode JSON node containing post details.
     * @return A ResponseEntity with a status code and a message.
     */
    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody JsonNode postNode) {
        Post post = JsonNodeToPost(postNode);
        if(post == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid post type");
        Byte postType = post.getPostType();
        UserEntity user = getUser();
        if(user == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
        long userId = user.getId();
        post.setAuthorID(userId);

        if(user.isBanned())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is banned");

        if(post instanceof TradingPost ) {
            boolean paidTradingPost = postType == 6;
            double price = ((SecondHandTradingPost) post).getPrice();
            if(price <= 0 && paidTradingPost)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Price must be positive");
            if(paidTradingPost && ((SecondHandTradingPost) post).getIBAN() != null) {
                String trimmedIBAN = ((SecondHandTradingPost) post).getIBAN().replaceAll("\\s", "");
                if(!validateIBAN(trimmedIBAN))
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("IBAN format is mistyped");
                ((SecondHandTradingPost) post).setIBAN(trimmedIBAN);
            } else if(paidTradingPost)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("IBAN cannot be empty");
            if(postType == 1) { //BorrowAndLendPost
                Instant instant = Instant.now();
                Instant trClock = instant.plus(Duration.ofHours(3));
                Timestamp now = Timestamp.from(trClock);
                Timestamp beginDate = ((BorrowAndLendPost)post).getBeginDate();
                Timestamp endDate = ((BorrowAndLendPost)post).getEndDate();
                if (!beginDate.after(now) || !endDate.after(now))
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Start and end dates cannot be from past");
                if (!endDate.after(beginDate))
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("End date must be after start date");
            }
        }
        if (post.getTitle() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title cannot be empty");
        if(post.getTitle().length() < 3)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title must have at least 3 characters");
        if(post.getTitle().length() > 40)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title can have at most 40 characters");
        if (post.getDescription() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Description cannot be empty");
        if(post.getDescription().length() < 4)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Description must have at least 4 characters");
        if(post.getDescription().length() > 300)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Description can have at most 300 characters");
        //TODO: When file system is added, check if imagePath is valid or not. If it is not present, do not check.
        if(postService.createOrSavePost(post)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server could not upload the post");
    }

    /**
     * Creates a new comment for a post.
     *
     * @param comment The comment to be created.
     * @return A ResponseEntity with a status code and a message.
     */
    @PostMapping("/createComment")
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        UserEntity user = getUser();
        if(user == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
        if(user.isBanned())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is banned");
        comment.setAuthorID(user.getId());
        Post primaryPost = postService.getPostByID(comment.getPrimaryPostID());
        if(primaryPost == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post does not exist");
        //If post is a secondary post, we need to add it to original comment's list
        if(!postService.createOrSaveComment(comment)) //TODO: delete already saved primary comment's element
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server could not save the comment");
        if(comment.getIsReply()) {
            Comment primaryComment = postService.getCommentByID(comment.getPrimaryCommentID());
            if(primaryComment == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Primary comment does not exist");
            if(primaryComment.getPrimaryPostID() != comment.getPrimaryPostID())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comments are not from the same post");
            primaryComment.addComment(comment.getId());
            if(!postService.createOrSaveComment(primaryComment))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server could not save the comment");
        }
        primaryPost.addComment(comment);
        if(!postService.createOrSavePost(primaryPost))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server could not save the post");
        return ResponseEntity.ok(comment);

    }

    /**
     * Retrieves all posts sorted by date.
     *
     * @return A ResponseEntity with a TreeSet of posts.
     */
    @GetMapping("/getAllPosts")
    public ResponseEntity<TreeSet<Post>> getAllPosts() {
        try {
            TreeSet<Post> allPosts = postService.getPostsSortedByDate();
            return ResponseEntity.ok(allPosts);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Votes on a post based on the provided post ID and vote type.
     *
     * @param postID The ID of the post to vote on.
     * @param type   The type of vote (upvote or downvote).
     * @return A ResponseEntity with a status code and a message.
     */
    @PostMapping("/vote/{postID}")
    public ResponseEntity<?> vote(@PathVariable long postID, @RequestBody String type) {
        long userId = getUser().getId();

        Post post = postService.getPostByID(postID);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post does not exist");
        }

        Vote vote = voteRepository.getVoteByVoterIDAndPostID(userId, postID);
        if(vote == null) {
            vote = new Vote(postID, userId, 0);
        }
        int previousVote = vote.getVoteValue();
        int voteValue;

        switch (type) {
            case "upvote":
                if(previousVote == 0 || previousVote == -1)
                    voteValue = 1;
                else
                    voteValue = 0;
                break;
            case "downvote":
                if(previousVote == 0 || previousVote == 1)
                    voteValue = -1;
                else
                    voteValue = 0;
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid vote type");
        }

        vote.setVoteValue(voteValue);
        post.setVotes(post.getVotes() + (voteValue - previousVote));
        if(!postService.createOrSavePost(post))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server could not save the post");
        voteRepository.save(vote);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves the currently authenticated user.
     *
     * @return The UserEntity representing the authenticated user.
     */
    private UserEntity getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserEntity) authentication.getPrincipal();
    }

    /**
     * Converts a JsonNode to a specific subtype of the Post class based on the "postType" field.
     *
     * @param postNode The JsonNode containing post details.
     * @return The Post object or null if the post type is invalid.
     */
    private Post JsonNodeToPost(JsonNode postNode) {
        JsonNode postTypeNode = postNode.get("postType");

        if (postTypeNode == null || !postTypeNode.isInt()) {
            return null;
        }

        int postType = postTypeNode.asInt();
        return switch (postType) {
            case 0 -> new ObjectMapper().convertValue(postNode, AnnouncementPost.class);
            case 1 -> new ObjectMapper().convertValue(postNode, BorrowAndLendPost.class);
            case 2 -> new ObjectMapper().convertValue(postNode, DonationPost.class);
            case 3 -> new ObjectMapper().convertValue(postNode, LostAndFoundPost.class);
            case 4 -> new ObjectMapper().convertValue(postNode, NormalPost.class);
            case 5 -> new ObjectMapper().convertValue(postNode, SectionExchangePost.class);
            case 6 -> new ObjectMapper().convertValue(postNode, SecondHandTradingPost.class);
            default -> null;
        };
    }

    /**
     * Validates an International Bank Account Number (IBAN) using a regular expression pattern.
     *
     * @param IBAN The IBAN to be validated.
     * @return True if the IBAN is valid, false otherwise.
     */
    private boolean validateIBAN(String IBAN) {
        Pattern IBAN_PATTERN = Pattern.compile("^(?i)([A-Z]{2}[ '+\\\\'-]?[0-9]{2})(?=(?:[ '+\\\\'-]?[A-Z0-9]){9,30}$)((?:[ '+\\\\'-]?[A-Z0-9]{3,5}){2,7})([ '+\\\\'-]?[A-Z0-9]{1,3})?$");
        return IBAN_PATTERN.matcher(IBAN).matches();
    }
}
