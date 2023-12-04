package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.DonationPost;
import tr.edu.bilkent.bilsync.repository.DonationPostRepository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

@Service
public class DonationPostService {

    private final DonationPostRepository donationPostRepository;

    public DonationPostService(DonationPostRepository donationPostRepository) {
        this.donationPostRepository = donationPostRepository;
    }

    public boolean register(DonationPost donationPost) {
        try {
            donationPost.setCommentList(new HashSet<>());
            donationPost.setViews(0);
            donationPost.setVotes(0);
            donationPost.setIsResolved(false);
            donationPost.setTakerID(-1);
            donationPost.setGiverID(donationPost.getAuthorID());
            donationPost.setIsMissedOut(false);
            donationPost.setIsHeld(false);
            ZonedDateTime currentTime = ZonedDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            donationPost.setPostDate(formattedTime);
            donationPostRepository.save(donationPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
