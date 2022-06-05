package com.android.challenge.yourself.be.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    private int createdBy;
    private int updateBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
