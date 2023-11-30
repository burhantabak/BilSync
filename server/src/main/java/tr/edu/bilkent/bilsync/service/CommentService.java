package tr.edu.bilkent.bilsync.service;

import tr.edu.bilkent.bilsync.dto.CommentReportRequestDTO;

public interface CommentService {
    void reportComment(CommentReportRequestDTO reportRequest);
}