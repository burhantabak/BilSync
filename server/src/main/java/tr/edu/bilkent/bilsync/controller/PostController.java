package tr.edu.bilkent.bilsync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.entity.DonationPostEntity;
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
    public ResponseEntity register(@RequestBody DonationPostEntity donationPost) {
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
