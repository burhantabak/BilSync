package tr.edu.bilkent.bilsync.service.PostServices;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.PostEntities.DonationPost;
import tr.edu.bilkent.bilsync.repository.PostRepositories.DonationPostRepository;

import java.util.List;

/**
 * Service class for managing DonationPost entities.
 */
@Service
public class DonationPostService {

    private final DonationPostRepository donationPostRepository;

    /**
     * Constructor for DonationPostService, injecting the required repository.
     *
     * @param donationPostRepository The repository for DonationPost entities.
     */
    public DonationPostService(DonationPostRepository donationPostRepository) {
        this.donationPostRepository = donationPostRepository;
    }

    /**
     * Creates or saves a DonationPost entity in the repository.
     *
     * @param donationPost The DonationPost entity to be created or saved.
     * @return True if the operation is successful, false otherwise.
     */
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

    /**
     * Retrieves a list of DonationPost entities sorted by date from the repository.
     *
     * @return A list of DonationPost entities sorted by date.
     */
    public List<DonationPost> getPostsSortedByDate() {
        return donationPostRepository.findDonationPostsSortedByDate();
    }

    /**
     * Retrieves a DonationPost entity by its ID from the repository.
     *
     * @param id The ID of the DonationPost entity to retrieve.
     * @return The DonationPost entity with the specified ID.
     */
    public DonationPost getPostByID(long id) {
        return donationPostRepository.findById(id);
    }

    /**
     * Deletes a DonationPost entity by its ID from the repository.
     *
     * @param id The ID of the DonationPost entity to delete.
     */
    public void deleteById(long id) {
        donationPostRepository.deleteById(id);
    }
}

