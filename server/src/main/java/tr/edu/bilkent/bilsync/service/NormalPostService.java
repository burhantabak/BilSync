package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.NormalPost;
import tr.edu.bilkent.bilsync.repository.NormalPostRepository;

@Service
public class NormalPostService {

    private final NormalPostRepository normalPostRepository;

    public NormalPostService(NormalPostRepository normalPostRepository) {
        this.normalPostRepository = normalPostRepository;
    }

    public boolean createPost(NormalPost normalPost) {
        try {
            normalPostRepository.save(normalPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
