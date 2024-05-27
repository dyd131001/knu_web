package com.knu.meeting.repository;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.vo.Address;
import java.util.List;
import java.util.Optional;

public interface MeetingRepository {
    Optional<Meeting> findById(Long id);
    List<Meeting> findMeetingsAfterId(Long id, Address address, Hobby hobby);
    void save(Meeting meeting);
    void delete(Meeting meeting);
    List<Meeting> findAllByAddressAndHobby(Address address, Hobby hobby);
    List<Meeting> findAllByLocationAndHobby(String locationName, Address address , Hobby hobby);
    List<Meeting> findAllByLocation(Location location, Hobby hobby);

    List<Meeting> findAll();
}

