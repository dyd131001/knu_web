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

/*
[
{
x: 23123
y: 2131
title: 그룹 1
info: 안녕하세요
road_address_name: 대구광역시 북구 대학로 80
link: 채팅
}
]


 */