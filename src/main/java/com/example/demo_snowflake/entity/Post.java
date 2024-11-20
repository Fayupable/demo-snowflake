package com.example.demo_snowflake.entity;

import com.example.demo_snowflake.util.SnowflakeUtil;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<Comment> comments;

    @PrePersist
    public void prePersist() {
        SnowflakeUtil.prePersist(this);
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}