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
                return ResponseEntity.badRequest().build();
        }
        setCommonPostFields(post);
        if(postService.createPost(post)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    private void setCommonPostFields(Post post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        long userId = userEntity.getId();
        post.setAuthorID(userId);
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<HashSet<Post>> getAllPosts() {
        try {
            HashSet<Post> allPosts = postService.getPostsSortedByDate();
            return ResponseEntity.ok(allPosts);
        } catch(Exception e) {
            System.out.println(e);
            HashSet<Post> allPosts = new HashSet<Post>();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    /*
    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public boolean createPost{}
    */ // todo
}
