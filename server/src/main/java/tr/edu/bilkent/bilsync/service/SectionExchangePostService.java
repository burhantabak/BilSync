package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.SectionExchangePost;
import tr.edu.bilkent.bilsync.repository.SectionExchangePostRepository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SectionExchangePostService {

    private final SectionExchangePostRepository sectionExchangePostRepository;

    public SectionExchangePostService(SectionExchangePostRepository sectionExchangePostRepository) {
        this.sectionExchangePostRepository = sectionExchangePostRepository;
    }

    public boolean register(SectionExchangePost sectionExchangePost) {
        try {
            ZonedDateTime currentTime = ZonedDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            sectionExchangePost.setPostDate(formattedTime);
            sectionExchangePostRepository.save(sectionExchangePost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}