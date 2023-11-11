package tr.edu.bilkent.bilsync.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonationPostController {
    @GetMapping("/donationTest")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }
}
