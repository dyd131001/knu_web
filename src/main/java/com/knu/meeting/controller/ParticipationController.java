package com.knu.meeting.controller;

import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.service.ParticipationService;
import com.knu.meeting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participations")
public class ParticipationController {

    @Autowired
    private ParticipationService participationService;
    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public List<Participation> getParticipationsByUser(@PathVariable Long userId) {

        User user = userService.findById(userId);
        return participationService.findByUser(user);
    }

    @GetMapping("/user/{userId}/past")
    public List<Participation> getPastParticipationsByUser(@PathVariable Long userId) {

        User user = userService.findById(userId);
        return participationService.findByUser(user);
    }

    // Other participation-related endpoints
}
