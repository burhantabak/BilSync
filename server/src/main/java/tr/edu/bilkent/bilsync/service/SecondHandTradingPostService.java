package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.SecondHandTradingPost;
import tr.edu.bilkent.bilsync.repository.SecondHandTradingPostRepository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SecondHandTradingPostService {

    private final SecondHandTradingPostRepository secondHandTradingPostRepository;

    public SecondHandTradingPostService(SecondHandTradingPostRepository secondHandTradingPostRepository) {
        this.secondHandTradingPostRepository = secondHandTradingPostRepository;
    }

    public boolean register(SecondHandTradingPost secondHandTradingPost) {
        try {
            ZonedDateTime currentTime = ZonedDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            secondHandTradingPost.setPostDate(formattedTime);
            secondHandTradingPostRepository.save(secondHandTradingPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}