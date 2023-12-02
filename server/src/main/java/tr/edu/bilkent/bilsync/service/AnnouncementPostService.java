package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.AnnouncementPost;
import tr.edu.bilkent.bilsync.repository.AnnouncementPostRepository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AnnouncementPostService {

    private final AnnouncementPostRepository announcementPostRepository;

    public AnnouncementPostService(AnnouncementPostRepository announcementPostRepository) {
        this.announcementPostRepository = announcementPostRepository;
    }

    public boolean register(AnnouncementPost announcementPost) {
        try {
            ZonedDateTime currentTime = ZonedDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            announcementPost.setPostDate(formattedTime);
            announcementPostRepository.save(announcementPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
