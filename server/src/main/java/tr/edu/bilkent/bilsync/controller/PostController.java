package tr.edu.bilkent.bilsync.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.entity.DonationPost;
import tr.edu.bilkent.bilsync.entity.User;
import tr.edu.bilkent.bilsync.service.PostService;

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

    @PostMapping("/createPost/createDonationPost")
    public ResponseEntity createPost(@RequestBody DonationPost donationPost) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long userId = Long.valueOf(user.getId());

        donationPost.setAuthorID(userId);
        if(postService.register(donationPost)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    /*
    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public boolean createPost{}
    */ // todo
}
