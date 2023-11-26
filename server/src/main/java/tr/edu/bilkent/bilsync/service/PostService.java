package tr.edu.bilkent.bilsync.service;

import tr.edu.bilkent.bilsync.entity.DonationPostEntity;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.repository.PostRepository;
import tr.edu.bilkent.bilsync.repository.UserRepository;

public class PostService {

    private final PostRepository postRepository;
    private final DonationPostService donationPostService;

    public PostService(PostRepository postRepository,
                       DonationPostService donationPostService) {
        this.postRepository = postRepository;
        this.donationPostService = donationPostService;
    }

    //public boolean register(DonationPostEntity donationPost) {
    //    return donationPostService.register(donationPost);
    //}
}
