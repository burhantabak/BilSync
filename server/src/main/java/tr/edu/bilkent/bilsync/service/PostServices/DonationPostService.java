package tr.edu.bilkent.bilsync.service.PostServices;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.PostEntities.DonationPost;
import tr.edu.bilkent.bilsync.repository.PostRepositories.DonationPostRepository;

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

    public void deleteById(long id) {
        donationPostRepository.deleteById(id);
    }
}
