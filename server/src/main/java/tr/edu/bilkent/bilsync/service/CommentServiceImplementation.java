// CommentServiceImpl.java
package tr.edu.bilkent.bilsync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.dto.CommentReportRequestDTO;
import tr.edu.bilkent.bilsync.entity.Comment;
import tr.edu.bilkent.bilsync.entity.Report;
import tr.edu.bilkent.bilsync.entity.User;
import tr.edu.bilkent.bilsync.repository.CommentRepository;
import tr.edu.bilkent.bilsync.repository.ReportRepository;
import tr.edu.bilkent.bilsync.repository.UserRepository;

import java.util.Optional;
@Service
public class CommentServiceImplementation implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReportRepository reportRepository;



    public void reportComment(CommentReportRequestDTO reportRequest) {

        // Find the requested comment first :

        Comment comment = commentRepository.findById(reportRequest.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comment is not found! "));

        User reporter = userRepository.findById(reportRequest.getReporterId())
                .orElseThrow(() -> new RuntimeException("Reporter is not found! "));

        // Create a Report entity to save data to DB
        Report report = new Report();
        report.setCommentId(reportRequest.getCommentId());
        report.setReporterId(reportRequest.getReporterId());
        report.setReason(reportRequest.getReason());

        reportRepository.save(report); // save to DB

    }


    }
