package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.DonationPost;
import tr.edu.bilkent.bilsync.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final DonationPostService donationPostService;

    public PostService(PostRepository postRepository,
                       DonationPostService donationPostService) {
        this.postRepository = postRepository;
        this.donationPostService = donationPostService;
    }

    public boolean register(DonationPost donationPost) {
        return donationPostService.register(donationPost);
    }
}
