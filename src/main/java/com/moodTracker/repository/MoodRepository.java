package com.moodTracker.repository;

import com.moodTracker.model.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoodRepository extends JpaRepository<Mood, Long> {
    List<Mood> findByUsername(String ownerId);
}
