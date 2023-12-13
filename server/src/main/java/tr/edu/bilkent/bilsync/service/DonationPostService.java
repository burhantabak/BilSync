package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.DonationPost;
import tr.edu.bilkent.bilsync.repository.DonationPostRepository;

import java.util.List;

@Service
public class DonationPostService {

    private final DonationPostRepository donationPostRepository;

    public DonationPostService(DonationPostRepository donationPostRepository) {
        this.donationPostRepository = donationPostRepository;
    }

    public boolean createOrSavePost(DonationPost donationPost) {
        try {
            donationPost.setGiverID(donationPost.getAuthorID());
            donationPostRepository.save(donationPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<DonationPost> getPostsSortedByDate() {
        return donationPostRepository.findDonationPostsSortedByDate();
    }

    public DonationPost getPostByID(long id) {
        return donationPostRepository.findById(id);
    }
}
