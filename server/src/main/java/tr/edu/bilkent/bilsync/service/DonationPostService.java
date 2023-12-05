package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.DonationPost;
import tr.edu.bilkent.bilsync.repository.DonationPostRepository;

@Service
public class DonationPostService {

    private final DonationPostRepository donationPostRepository;

    public DonationPostService(DonationPostRepository donationPostRepository) {
        this.donationPostRepository = donationPostRepository;
    }

    public boolean createPost(DonationPost donationPost) {
        try {
            donationPost.setGiverID(donationPost.getAuthorID());
            donationPostRepository.save(donationPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
