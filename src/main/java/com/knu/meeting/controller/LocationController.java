package com.knu.meeting.controller;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.dto.CreateLocationDTO;
import com.knu.meeting.model.dto.CreateMeetingDTO;
import com.knu.meeting.model.dto.LocationDTO;
import com.knu.meeting.model.dto.MeetingDTO;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.Position;
import com.knu.meeting.service.LocationService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/locations")
@Transactional
@AllArgsConstructor
public class LocationController {
    private LocationService locationService;

    @PostMapping("/find/{id}")
    public LocationDTO findLocation(@PathVariable("id") Long id){
        return locationService.findLocationById(id);
    }
    @PostMapping("/new")
    public Long create(@RequestBody CreateLocationDTO createLocationDTO) {
        return locationService.create(createLocationDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLocation(@PathVariable("id") Long id){
        locationService.delete(id);
    }

    @PostMapping("/findAll")
    public List<LocationDTO> findAll(){
        return locationService.findAll();
    }

    @GetMapping("/search")
    public List<LocationDTO> search(
            @RequestParam Address address,
            @RequestParam Hobby hobby
            ){
        return locationService.findAllByAddressAndHobby(address,hobby);
    }

    @PostMapping("/update/{id}/locationName")
    public boolean updateLocationName(@PathVariable("id") Long id, @RequestBody String locationName) {
        return locationService.updateLocationName(id,locationName);
    }

    @PostMapping("/update/{id}/position")
    public boolean updatePosition(@PathVariable("id") Long id, @RequestBody Position position) {
        return locationService.updatePosition(id, position);
    }



}
