package com.moodTracker.controller;

import com.moodTracker.model.Mood;
import com.moodTracker.model.MoodService;
import com.moodTracker.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/moods")
public class MoodController {

    private final MoodRepository repository;

    @Autowired
    public MoodController(MoodRepository repository) {
        this.repository = repository;
    }

    public record MoodRequest(
            Long moodId,
            String moodValue,
            String comment,
            Instant date,
            Instant updatedAt,
            String username
    ) {}

    public record MoodResponse(
            Long moodId,
            String moodValue,
            String comment,
            Instant date,
            Instant updatedAt,
            String username
    ) {}

    private MoodService moodService;

    @PostMapping
    public MoodResponse save(@RequestBody MoodRequest body,
                     @AuthenticationPrincipal UserDetails userDetails) {
        Mood mood = repository.save(new Mood(
                body.moodId(),
                body.moodValue(),
                body.comment(),
                body.date(),
                Instant.now(),
                userDetails.getUsername()
        ));
        return toResponse(mood);
    }

    // GET http://localhost:8081/mood?ownerId=123
    /**
     * Retrieves a list of [MoodResponse] objects associate with the specified owner id.
     *

     */
    @GetMapping
    public List<MoodResponse> findByUsername(@AuthenticationPrincipal UserDetails userDetails) {
        return repository
                .findByUsername(userDetails.getUsername()).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{moodId}")
    public void deleteMood(@PathVariable Long moodId) {
        repository.deleteById(moodId);
    }

    private MoodResponse toResponse(Mood mood) {
        return new MoodResponse (
                mood.getMoodId(),
                mood.getMoodValue(),
                mood.getComment(),
                mood.getDate(),
                mood.getUpdatedAt(),
                mood.getUsername());
    }

    private MoodRequest toRequest(Mood mood) {
        return new MoodRequest (
                mood.getMoodId(),
                mood.getMoodValue(),
                mood.getComment(),
                mood.getDate(),
                mood.getUpdatedAt(),
                mood.getUsername());
    }

}
