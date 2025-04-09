package com.moodTracker.model;

import com.moodTracker.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MoodServiceImplementation implements MoodService {

    @Autowired
    private MoodRepository moodRepository;

    @Override
    public Mood createMood(String moodValue, String comment, String username) {
        Mood mood = new Mood();
        mood.setMoodValue(moodValue);
        mood.setComment(comment);
        mood.setDate(Instant.now());
        mood.setUpdatedAt(Instant.now());
        mood.setUsername(username);
        return moodRepository.save(mood);
    }

    @Override
    public Mood updateMood(Long moodId, String moodValue, String comment, Long ownerId) {
        Mood mood = moodRepository.findById(moodId).orElseThrow(() -> new RuntimeException("Note not found"));
        mood.setMoodValue(moodValue);
        mood.setComment(comment);
        mood.setUpdatedAt(Instant.now());
        return moodRepository.save(mood);
    }

    @Override
    public void deleteNote(Long moodId, String username) {
        moodRepository.deleteById(moodId);
    }

    @Override
    public List<Mood> getMoodsForUser(String ownerId) {
        return moodRepository.findByUsername(ownerId);
    }
}
