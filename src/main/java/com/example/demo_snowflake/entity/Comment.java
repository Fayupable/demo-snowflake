package com.example.demo_snowflake.entity;

import com.example.demo_snowflake.config.SnowflakeGenerator;
import com.example.demo_snowflake.util.SnowflakeUtil;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    private Long id;

    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonBackReference
    private Post post;

    @PrePersist
    public void prePersist() {
        SnowflakeUtil.prePersist(this);
//        SnowflakeUtil.assignId(this);
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}