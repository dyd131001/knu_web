package com.knu.meeting.service;

import com.knu.meeting.mapper.LocationMapper;
import com.knu.meeting.mapper.MeetingMapper;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.dto.CreateLocationDTO;
import com.knu.meeting.model.dto.CreateMeetingDTO;
import com.knu.meeting.model.dto.LocationDTO;
import com.knu.meeting.model.dto.MeetingDTO;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.Position;
import com.knu.meeting.model.vo.PossibleAge;
import com.knu.meeting.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class LocationService {

    private LocationRepository locationRepository;

    public LocationDTO findLocationById(Long id){
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("location not found with id: " + id));

        return LocationMapper.INSTANCE.toDTO(location);
    }
    public Long create(CreateLocationDTO createLocationDTO) {

        Location location = LocationMapper.INSTANCE.createLocationDTOToEntity(createLocationDTO);
        log.info("location={} ", location);
        locationRepository.save(location);

        return location.getId();

    }

    public void delete(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("location not found with id: " + id));
        locationRepository.delete(location);
    }

    public boolean updateLocationName(Long id, String locationName) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("location not found with id: " + id));
        location.updateLocationName(locationName);
        locationRepository.save(location);
        return location.getLocationName().equals(locationName);
    }

    public boolean updatePosition(Long id, Position position) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("location not found with id: " + id));
        location.updatePosition(position);
        locationRepository.save(location);
        return location.getPosition().equals(position);
    }



    public List<LocationDTO> findAllByAddressAndHobby(Address address, Hobby hobby){
        //Hibernate.initialize(meeting.getLocation());
        return locationRepository.findAllByAddressAndHobby(address,hobby)
                .stream()
                .map(LocationMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public List<LocationDTO> findAll() {
        //Hibernate.initialize(location());
        return locationRepository.findAll()
                .stream()
                .map(LocationMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

}
