package com.knu.meeting.mapper;

import com.knu.meeting.model.dto.CreateMeetingDTO;
import com.knu.meeting.model.dto.EnterMeetingDTO;
import com.knu.meeting.model.dto.MeetingDTO;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.model.vo.Position;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-28T03:57:21+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class MeetingMapperImpl implements MeetingMapper {

    @Override
    public Meeting createMeetingDTOToEntity(CreateMeetingDTO createMeetingDTO, Location location, User user) {
        if ( createMeetingDTO == null && location == null && user == null ) {
            return null;
        }

        Meeting.MeetingBuilder meeting = Meeting.builder();

        if ( createMeetingDTO != null ) {
            meeting.hobbies( MeetingMapper.toHobbies( createMeetingDTO.getHobbies() ) );
            meeting.title( createMeetingDTO.getTitle() );
            meeting.possibleAge( createMeetingDTO.getPossibleAge() );
        }
        meeting.location( location );
        meeting.creator( user );

        return meeting.build();
    }

    @Override
    public MeetingDTO toMeetingDTO(Meeting meeting) {
        if ( meeting == null ) {
            return null;
        }

        MeetingDTO meetingDTO = new MeetingDTO();

        meetingDTO.setHobbies( MeetingMapper.toHobbiesString( meeting.getHobbies() ) );
        meetingDTO.setPosition( meetingLocationPosition( meeting ) );
        meetingDTO.setId( meeting.getId() );
        meetingDTO.setPossibleAge( meeting.getPossibleAge() );
        meetingDTO.setTimeStatus( meeting.getTimeStatus() );

        return meetingDTO;
    }

    @Override
    public EnterMeetingDTO toEnterMeetingDTO(Meeting meeting) {
        if ( meeting == null ) {
            return null;
        }

        EnterMeetingDTO enterMeetingDTO = new EnterMeetingDTO();

        enterMeetingDTO.setLocationId( meetingLocationId( meeting ) );
        enterMeetingDTO.setMessages( MeetingMapper.toMessageDTO( meeting.getMessages() ) );
        enterMeetingDTO.setUsers( MeetingMapper.toUserDTO( meeting.getParticipations() ) );
        enterMeetingDTO.setId( meeting.getId() );
        enterMeetingDTO.setTitle( meeting.getTitle() );

        return enterMeetingDTO;
    }

    private Position meetingLocationPosition(Meeting meeting) {
        if ( meeting == null ) {
            return null;
        }
        Location location = meeting.getLocation();
        if ( location == null ) {
            return null;
        }
        Position position = location.getPosition();
        if ( position == null ) {
            return null;
        }
        return position;
    }

    private Long meetingLocationId(Meeting meeting) {
        if ( meeting == null ) {
            return null;
        }
        Location location = meeting.getLocation();
        if ( location == null ) {
            return null;
        }
        Long id = location.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
