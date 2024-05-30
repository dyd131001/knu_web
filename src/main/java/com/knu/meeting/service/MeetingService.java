package com.knu.meeting.service;

import com.knu.meeting.mapper.MeetingMapper;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.dto.CreateMeetingDTO;
import com.knu.meeting.model.dto.EnterMeetingDTO;
import com.knu.meeting.model.dto.MeetingDTO;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.PossibleAge;
import com.knu.meeting.repository.LocationRepository;
import com.knu.meeting.repository.MeetingRepository;
import com.knu.meeting.repository.ParticipationRepository;
import com.knu.meeting.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class MeetingService {

    private MeetingRepository meetingRepository;
    private UserRepository userRepository;
    private LocationRepository locationRepository;
    private ParticipationRepository participationRepository;


    public MeetingDTO findMeetingById(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("meeting not found with id: " + id));

//        Hibernate.initialize(meeting.getLocation());

        return MeetingMapper.INSTANCE.toMeetingDTO(meeting);
    }

    public EnterMeetingDTO findEnterMeetingById(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("meeting not found with id: " + id));

//        Hibernate.initialize(meeting.getLocation());
//        Hibernate.initialize(meeting.getMessages());

        return MeetingMapper.INSTANCE.toEnterMeetingDTO(meeting);
    }


    public boolean updateTitle(Long id, String title) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("meeting not found with id: " + id));;
        meeting.updateTitle(title);
        meetingRepository.save(meeting);
        return meeting.getTitle().equals(title);
    }

    public boolean updateLocation(Long id, Location location) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("meeting not found with id: " + id));;
        meeting.updateLocation(location);
        meetingRepository.save(meeting);
        return meeting.getLocation().equals(location);
    }

    public boolean updatePossibleAge(Long id, PossibleAge possibleAge) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("meeting not found with id: " + id));
        meeting.updatePossibleAge(possibleAge);
        meetingRepository.save(meeting);
        return meeting.getPossibleAge().equals(possibleAge);
    }

    public boolean updateHobbies(Long id , List<String> hobbies){
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("meeting not found with id: " + id));
        List<Hobby> hobbiesEnum = hobbies.stream().map(Hobby::findHobby)
                .collect(Collectors.toList());
        meeting.updateHobbies(hobbiesEnum);
        meetingRepository.save(meeting);
        return meeting.getHobbies().equals(hobbiesEnum);
    }



    public List<MeetingDTO> findMeetingsAfterId(Long id, Address address, Hobby hobby) {
        return meetingRepository.findMeetingsAfterId(id,address,hobby)
                .stream()
                .map(meeting -> {
                    //Hibernate.initialize(meeting.getLocation());
                    return MeetingMapper.INSTANCE.toMeetingDTO(meeting);
                })
                .collect(Collectors.toList());
    }

    public Long create(CreateMeetingDTO createMeetingDTO) {
        User user = userRepository.findById(createMeetingDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("user not found with id: " + createMeetingDTO.getUserId()));

        Location location = locationRepository.findById(createMeetingDTO.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException("location not found with id: " + createMeetingDTO.getLocationId()));

        Meeting meeting = MeetingMapper.INSTANCE.createMeetingDTOToEntity(createMeetingDTO,location,user);
        meetingRepository.save(meeting);

        Participation participation = Participation.builder()
                .meeting(meeting)
                .user(user)
                .build();

        participationRepository.save(participation);
        return meeting.getId();

    }

    public void delete(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("meeting not found with id: " + id));
        meetingRepository.delete(meeting);
    }

    public List<MeetingDTO> findAllByAddressAndHobby(Address address, Hobby hobby) {
        return meetingRepository.findAllByAddressAndHobby( address , hobby)
                .stream()
                .map(meeting -> {
                    //Hibernate.initialize(meeting.getLocation());
                    return MeetingMapper.INSTANCE.toMeetingDTO(meeting);
                })
                .collect(Collectors.toList());
    }


    public List<MeetingDTO> findAllByLocationAndHobbies(String locationName, Address address, Hobby hobby) {
        return meetingRepository.findAllByLocationAndHobby( locationName, address , hobby)
                .stream()
                .map(meeting -> {
                    //Hibernate.initialize(meeting.getLocation());
                    return MeetingMapper.INSTANCE.toMeetingDTO(meeting);
                })
                .collect(Collectors.toList());
    }

    public List<MeetingDTO> findAllByLocation(Long locationId, Hobby hobby){
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("location not found with id: " + locationId));

        return meetingRepository.findAllByLocation(location,hobby)
                .stream()
                .map(meeting -> {
                    //Hibernate.initialize(meeting.getLocation());
                    return MeetingMapper.INSTANCE.toMeetingDTO(meeting);
                })
                .collect(Collectors.toList());

    }
    public List<MeetingDTO> findAll() {
        return meetingRepository.findAll()
                .stream()
                .map(meeting -> {
                    //Hibernate.initialize(meeting.getLocation());
                    return MeetingMapper.INSTANCE.toMeetingDTO(meeting);
                })
                .collect(Collectors.toList());
    }
}
