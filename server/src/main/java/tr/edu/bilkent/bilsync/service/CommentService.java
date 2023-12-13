package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.Comment;
import tr.edu.bilkent.bilsync.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment getCommentByID(long id) {
        return commentRepository.findById(id);
    }

    public boolean createOrSaveComment(Comment comment) {
        try {
            commentRepository.save(comment);
            return true;
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
