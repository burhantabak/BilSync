package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.SectionExchangePost;
import tr.edu.bilkent.bilsync.repository.SectionExchangePostRepository;

import java.util.List;

@Service
public class SectionExchangePostService {

    private final SectionExchangePostRepository sectionExchangePostRepository;

    public SectionExchangePostService(SectionExchangePostRepository sectionExchangePostRepository) {
        this.sectionExchangePostRepository = sectionExchangePostRepository;
    }

    public boolean createOrSavePost(SectionExchangePost sectionExchangePost) {
        try {
            sectionExchangePost.setGiverID(sectionExchangePost.getAuthorID());
            sectionExchangePostRepository.save(sectionExchangePost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<SectionExchangePost> getPostsSortedByDate() {
        return sectionExchangePostRepository.findSectionExchangePostsSortedByDate();
    }

    public SectionExchangePost getPostByID(long id) {
        return sectionExchangePostRepository.findById(id);
    }

    public void deleteById(long id) {
        sectionExchangePostRepository.deleteById(id);
    }
}