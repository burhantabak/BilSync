package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.Comment;
import tr.edu.bilkent.bilsync.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    /**
     * Constructor for CommentService, injecting the required repository.
     *
     * @param commentRepository The repository for Comment entities.
     */
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Retrieves a Comment entity by its ID from the repository.
     *
     * @param id The ID of the Comment entity to retrieve.
     * @return The Comment entity with the specified ID.
     */
    public Comment getCommentByID(long id) {
        return commentRepository.findById(id);
    }

    /**
     * Creates or saves a Comment entity in the repository.
     *
     * @param comment The Comment entity to be created or saved.
     * @return True if the operation is successful, false otherwise.
     */
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
