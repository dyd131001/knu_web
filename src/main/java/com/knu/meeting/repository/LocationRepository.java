package com.knu.meeting.repository;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.dto.MeetingDTO;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.model.vo.Address;
import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    Optional<Location> findById(Long id);
    void save(Location location);
    void delete(Location location);

    List<Location> findAllByAddressAndHobby(Address address, Hobby hobby);
    List<Location> findAll();
}
