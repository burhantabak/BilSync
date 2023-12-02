package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.LostAndFoundPost;
import tr.edu.bilkent.bilsync.repository.LostAndFoundPostRepository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LostAndFoundPostService {
    private final LostAndFoundPostRepository lostAndFoundPostRepository;

    public LostAndFoundPostService(LostAndFoundPostRepository lostAndFoundPostRepository) {
        this.lostAndFoundPostRepository = lostAndFoundPostRepository;
    }

    public boolean register(LostAndFoundPost lostAndFoundPost) {
        try {
            ZonedDateTime currentTime = ZonedDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            lostAndFoundPost.setPostDate(formattedTime);
            lostAndFoundPostRepository.save(lostAndFoundPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
