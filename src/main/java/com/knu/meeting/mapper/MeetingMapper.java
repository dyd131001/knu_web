package com.knu.meeting.mapper;

import com.knu.meeting.model.constant.Gender;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.constant.Role;
import com.knu.meeting.model.dto.CreateMeetingDTO;
import com.knu.meeting.model.dto.CreateMessageDTO;
import com.knu.meeting.model.dto.CreateUserDTO;
import com.knu.meeting.model.dto.DetailUserDTO;
import com.knu.meeting.model.dto.EnterMeetingDTO;
import com.knu.meeting.model.dto.GroupMessageDTO;
import com.knu.meeting.model.dto.MeetingDTO;
import com.knu.meeting.model.dto.UserDTO;
import com.knu.meeting.model.entity.GroupMessage;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MeetingMapper {

    MeetingMapper INSTANCE = Mappers.getMapper(MeetingMapper.class);

    @Mapping(source = "createMeetingDTO.hobbies", target = "hobbies", qualifiedByName = "toHobbiesEnum")
    @Mapping(source = "user", target = "creator")
    Meeting createMeetingDTOToEntity(
            CreateMeetingDTO createMeetingDTO,
            Location location,
            User user);


    @Mapping(source = "meeting.hobbies", target = "hobbies", qualifiedByName = "toHobbiesString")
    //@Mapping(source = "meeting.location.position", target = "position")
    MeetingDTO toMeetingDTO(Meeting meeting);

    @Mapping(source = "meeting.location.id", target = "locationId")
    @Mapping(source = "meeting.messages", target = "messages", qualifiedByName = "toMessageDTO")
    @Mapping(source = "meeting.participations", target = "users", qualifiedByName = "toUserDTO")
    EnterMeetingDTO toEnterMeetingDTO(Meeting meeting);


    @Named("toMessageDTO")
    static List<GroupMessageDTO> toMessageDTO(List<GroupMessage> messages){
        return messages.stream()
                .map(GroupMessageMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Named("toUserDTO")
    static List<UserDTO> toUserDTO(List<Participation> participations){
        List<User> users =  participations.stream()
                .map(Participation::getUser)
                .collect(Collectors.toList());

        return users.stream()
                .map(UserMapper.INSTANCE::toUserDTO)
                .collect(Collectors.toList());
    }

    @Named("toHobbiesEnum")
    static List<Hobby> toHobbies(List<String> hobbies){
        return hobbies.stream().map(Hobby::findHobby)
                .collect(Collectors.toList());
    }
    @Named("toHobbiesString")
    static List<String> toHobbiesString(List<Hobby> hobbies){
        return hobbies.stream().map(hobby -> hobby.getValue())
                .collect(Collectors.toList());
    }
}
