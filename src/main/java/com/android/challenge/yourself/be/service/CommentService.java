package com.android.challenge.yourself.be.service;

import com.android.challenge.yourself.be.model.entities.ReportedComment;
import com.android.challenge.yourself.be.model.entities.UserComment;
import com.android.challenge.yourself.be.repository.ReportedCommentRepository;
import com.android.challenge.yourself.be.repository.UserCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private UserCommentRepository commentRepository;
    @Autowired
    private ReportedCommentRepository reportedCommentRepository;
    private final static int pageSize = 20;

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
            UserComment comment = commentRepository.findById(reportedComment.getUserComment().getId()).get();
            comment.setReports(comment.getReports() + 1);
            commentRepository.save(comment);
            reportedCommentRepository.save(reportedComment);
            isSaved = true;
        }
        return isSaved;
    }

    public UserComment getComment(int id) {
        return commentRepository.findById(id).get();
    }

    public Page<UserComment> getComments(int page) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return commentRepository.findByOrderByReportsDescCreatedAtDesc(pageable);
    }

    public List<UserComment> getComments(LocalDate date) {
        return commentRepository.findByCreatedAtOrderByReportsDesc(date);
    }
}
