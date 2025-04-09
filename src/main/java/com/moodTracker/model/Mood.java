package com.moodTracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Mood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moodId;
    private String moodValue;
    @Lob
    private String comment;
    private Instant date;
    private Instant updatedAt;
    private String username;

    public Mood() {}

    public Mood(Long moodId, String moodValue, String comment, Instant date, Instant updatedAt, String ownerId) {
        this.moodId = moodId;
        this.moodValue = moodValue;
        this.comment = comment;
        this.date = date;
        this.updatedAt = updatedAt;
        this.username = ownerId;
    }

    public Long getMoodId() {
        return moodId;
    }

    public void setMoodId(Long moodId) {
        this.moodId = moodId;
    }

    public String getMoodValue() {
        return moodValue;
    }

    public void setMoodValue(String moodValue) {
        this.moodValue = moodValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
