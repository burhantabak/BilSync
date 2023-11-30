// CommentServiceImpl.java
package tr.edu.bilkent.bilsync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.dto.CommentReportRequestDTO;
import tr.edu.bilkent.bilsync.entity.Comment;
import tr.edu.bilkent.bilsync.entity.User;
import tr.edu.bilkent.bilsync.repository.CommentRepository;
import tr.edu.bilkent.bilsync.repository.UserRepository;

import java.util.Optional;
@Service
public class CommentServiceImplementation implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;



    public void reportComment(CommentReportRequestDTO reportRequest) {
        // will be implemented
        // trying to maintain logic for now
    }


    }
