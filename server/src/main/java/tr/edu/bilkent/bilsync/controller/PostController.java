package tr.edu.bilkent.bilsync.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
    @GetMapping()
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }
    /*
    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public boolean createPost{}
    */
}
