package tr.edu.bilkent.bilsync.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.entity.*;
import tr.edu.bilkent.bilsync.service.PostService;

import java.util.HashSet;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) { this.postService = postService; }

    @GetMapping()
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody JsonNode postNode) {
        Post post = JsonNodeToPost(postNode);
        if(post == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID_POST_TYPE");
        Byte postType = post.getPostType();
        UserEntity user = getUser();
        if(user == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USER_DOES_NOT_EXIST");
        long userId = user.getId();
        post.setAuthorID(userId);

        if(user.getIsBanned())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("USER_IS_BANNED");

        if(post instanceof TradingPost ) {
            double price = ((TradingPost) post).getPrice();
            if(price < 0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PRICE_LESS_THAN_0");
            if((postType != 1 && postType != 2) && price == 0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PRICE_CANNOT_BE_0");
            if(postType != 1 && postType != 2) {
                String trimmedIBAN = ((TradingPost) post).getIBAN().replaceAll("\\s", "");
                if(!validateIBAN(trimmedIBAN))
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("IBAN_WAS_MISTYPED");
                ((TradingPost) post).setIBAN(trimmedIBAN);
            }
        }
        if (post.getTitle() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TITLE_CANNOT_BE_EMPTY");
        if(post.getTitle().length() < 10)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TITLE_MUST_HAVE_AT_LEAST_10_CHARACTERS");
        if(post.getTitle().length() > 100)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TITLE_CAN_HAVE_AT_MOST_100_CHARACTERS");
        if (post.getDescription() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DESCRIPTION_CANNOT_BE_EMPTY");
        if(post.getDescription().length() < 10)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DESCRIPTION_MUST_HAVE_AT_LEAST_10_CHARACTERS");
        if(post.getDescription().length() > 3000)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DESCRIPTION_CAN_HAVE_AT_MOST_3000_CHARACTERS");
        //TODO: When file system is added, check if imagePath is valid or not. If it is not present, do not check.
        if(postService.createOrSavePost(post)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("POST_COULD_NOT_BE_SAVED_TO_DATABASE");
    }

    @PostMapping("/createComment")
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserEntity userEntity = (UserEntity) authentication.getPrincipal();
            comment.setAuthorID(userEntity.getId());
            Post primaryPost = postService.getPostByID(comment.getPrimaryPostID());
            if(primaryPost == null) return ResponseEntity.badRequest().build();
            //If post is a secondary post, we need to add it to original comment's list
            if(comment.getIsReply()) {
                Comment primaryComment = postService.getCommentByID(comment.getPrimaryCommentID());
                if(primaryComment == null) return ResponseEntity.badRequest().build();
                primaryComment.addComment(comment);
                postService.createOrSaveComment(primaryComment);
            }
            postService.createOrSaveComment(comment);
            primaryPost.addComment(comment);
            postService.createOrSavePost(primaryPost);
            return ResponseEntity.ok(comment);
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<HashSet<Post>> getAllPosts() {
        try {
            HashSet<Post> allPosts = postService.getPostsSortedByDate();
            return ResponseEntity.ok(allPosts);
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/vote/{postID}")
    public ResponseEntity<?> vote(@PathVariable long postID, @RequestParam String type) {
        long userId = getUser().getId();

        Post post = postService.getPostByID(postID);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("POST_DOESNT_EXIST");
        }

        Integer votes = post.getVoters().get(userId);
        int voteValue = 0;

        switch (type) {
            case "upvote":
                voteValue = votes == null ? 1 : (votes == 1 ? -1 : (votes == 0 ? 1 : 2));
                break;
            case "downvote":
                voteValue = votes == null ? -1 : (votes == -1 ? 1 : (votes == 0 ? -1 : -2));
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid vote type");
        }

        post.getVoters().put(userId, Integer.compare(voteValue, 0));
        post.setVotes(post.getVotes() + voteValue);
        postService.createOrSavePost(post);
        return ResponseEntity.ok().build();
    }

    private UserEntity getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserEntity) authentication.getPrincipal();
    }

    private Post JsonNodeToPost(JsonNode postNode) {
        JsonNode postTypeNode = postNode.get("postType");

        if (postTypeNode == null || !postTypeNode.isInt()) {
            return null;
        }

        int postType = postTypeNode.asInt();
        Post post = switch (postType) {
            case 0 -> new ObjectMapper().convertValue(postNode, AnnouncementPost.class);
            case 1 -> new ObjectMapper().convertValue(postNode, BorrowAndLendPost.class);
            case 2 -> new ObjectMapper().convertValue(postNode, DonationPost.class);
            case 3 -> new ObjectMapper().convertValue(postNode, LostAndFoundPost.class);
            case 4 -> new ObjectMapper().convertValue(postNode, NormalPost.class);
            case 5 -> new ObjectMapper().convertValue(postNode, SectionExchangePost.class);
            case 6 -> new ObjectMapper().convertValue(postNode, SecondHandTradingPost.class);
            default -> null;
        };

        return post;
    }

    private boolean validateIBAN(String IBAN) {
        Pattern IBAN_PATTERN = Pattern.compile("^(?i)([A-Z]{2}[ '+\\\\'-]?[0-9]{2})(?=(?:[ '+\\\\'-]?[A-Z0-9]){9,30}$)((?:[ '+\\\\'-]?[A-Z0-9]{3,5}){2,7})([ '+\\\\'-]?[A-Z0-9]{1,3})?$");
        return IBAN_PATTERN.matcher(IBAN).matches();
    }
}
