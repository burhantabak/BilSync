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

@RestController
@CrossOrigin
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody JsonNode postNode) {
        Post post = JsonNodeToPost(postNode);
        Byte postType = post.getPostType();
        if(post instanceof TradingPost ) {
            double price = ((TradingPost) post).getPrice();
            if(price < 0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PRICE_LESS_THAN_0");
            if(price == 0 && (postType != 1 && postType != 2))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PRICE_CANNOT_BE_0");
            if(((TradingPost) post).getIBAN() == null && (postType != 1 && postType != 2))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("IBAN_MUST_BE_ENTERED");
        }
        long userId = getUser().getId();
        post.setAuthorID(userId);
        if(postService.createOrSavePost(post)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
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
        int postType = postNode.get("postType").asInt();
        Post post;

        switch (postType) {
            case 0:
                post = new ObjectMapper().convertValue(postNode, AnnouncementPost.class);
                break;
            case 1:
                post = new ObjectMapper().convertValue(postNode, BorrowAndLendPost.class);
                break;
            case 2:
                post = new ObjectMapper().convertValue(postNode, DonationPost.class);
                break;
            case 3:
                post = new ObjectMapper().convertValue(postNode, LostAndFoundPost.class);
                break;
            case 4:
                post = new ObjectMapper().convertValue(postNode, NormalPost.class);
                break;
            case 5:
                post = new ObjectMapper().convertValue(postNode, SectionExchangePost.class);
                break;
            case 6:
                post = new ObjectMapper().convertValue(postNode, SecondHandTradingPost.class);
                break;
            default:
                post = null;
        }
        return post;
    }
}
