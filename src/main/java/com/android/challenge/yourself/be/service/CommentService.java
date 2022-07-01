package com.android.challenge.yourself.be.service;

import com.android.challenge.yourself.be.model.entities.ReportedComment;
import com.android.challenge.yourself.be.model.entities.UserComment;
import com.android.challenge.yourself.be.repository.ReportedCommentRepository;
import com.android.challenge.yourself.be.repository.UserCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private UserCommentRepository commentRepository;
    @Autowired
    private ReportedCommentRepository reportedCommentRepository;

    public UserComment saveComment(UserComment userComment) {
        return commentRepository.save(userComment);
    }

    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }

    public boolean saveReportedComment(ReportedComment reportedComment) {
        boolean isSaved = false;
        ReportedComment foundComment = reportedCommentRepository.findByUserIdAndUserCommentId(reportedComment.getUser().getId(), reportedComment.getUserComment().getId());
        if (null == foundComment) {
            reportedCommentRepository.save(reportedComment);
            isSaved = true;
        }
        return isSaved;
    }

    public UserComment getComment(int id) {
        return commentRepository.findById(id).get();
    }

}
