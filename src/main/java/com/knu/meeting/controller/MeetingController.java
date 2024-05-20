package com.knu.meeting.controller;

import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @GetMapping("/after/{id}")
    public List<Meeting> getMeetingsAfterId(@PathVariable Long id) {
        return meetingService.findMeetingsAfterId(id);
    }

    @GetMapping("/search")
    public List<Meeting> searchMeetings(@RequestParam String title, @RequestParam int page, @RequestParam int size) {
        return meetingService.findByTitleContaining(title, page, size);
    }

    // Other meeting-related endpoints
}
