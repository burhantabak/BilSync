package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.NormalPost;
import tr.edu.bilkent.bilsync.repository.NormalPostRepository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NormalPostService {

    private final NormalPostRepository normalPostRepository;

    public NormalPostService(NormalPostRepository normalPostRepository) {
        this.normalPostRepository = normalPostRepository;
    }

    public boolean createPost(NormalPost normalPost) {
        try {
            ZonedDateTime currentTime = ZonedDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            normalPost.setPostDate(formattedTime);
            normalPostRepository.save(normalPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
