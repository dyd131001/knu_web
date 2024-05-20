package com.knu.meeting.repository;

import com.knu.meeting.model.entity.Meeting;
import java.util.List;

public interface MeetingRepository {
    Meeting findById(Long id);
    List<Meeting> findMeetingsAfterId(Long id);
    void save(Meeting meeting);
    void delete(Meeting meeting);
    List<Meeting> findByTitleContaining(String title, int page, int size);
    List<Meeting> findAll();
}

