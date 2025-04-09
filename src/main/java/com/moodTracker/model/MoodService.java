package com.moodTracker.model;

import java.util.List;

public interface MoodService {

    Mood createMood(String moodValue, String comment, String ownerId);

    Mood updateMood(Long moodId, String moodValue, String comment, Long ownerId);

    void deleteNote(Long moodId, String ownerId);

    List<Mood> getMoodsForUser(String ownerId);
}
