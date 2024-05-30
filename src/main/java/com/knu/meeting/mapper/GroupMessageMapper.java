package com.knu.meeting.mapper;

import com.knu.meeting.model.dto.CreateMessageDTO;
import com.knu.meeting.model.dto.GroupMessageDTO;
import com.knu.meeting.model.dto.MeetingDTO;
import com.knu.meeting.model.dto.UserDTO;
import com.knu.meeting.model.entity.GroupMessage;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMessageMapper {

    GroupMessageMapper INSTANCE = Mappers.getMapper(GroupMessageMapper.class);

    @Mapping(source = "user", target = "creator")
    @Mapping(source = "meeting", target = "meeting")
    GroupMessage CreateMessageDTOToEntity(
            CreateMessageDTO createMessageDTO,
            User user,
            Meeting meeting
    );

    @Mapping(source = "creator.username", target = "username")
    GroupMessageDTO toDTO(GroupMessage groupMessage);


}
