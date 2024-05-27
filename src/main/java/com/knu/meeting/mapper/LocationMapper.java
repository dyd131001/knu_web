package com.knu.meeting.mapper;

import com.knu.meeting.model.constant.Gender;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.constant.Role;
import com.knu.meeting.model.dto.CreateLocationDTO;
import com.knu.meeting.model.dto.CreateUserDTO;
import com.knu.meeting.model.dto.DetailUserDTO;
import com.knu.meeting.model.dto.LocationDTO;
import com.knu.meeting.model.dto.MeetingDTO;
import com.knu.meeting.model.dto.UserDTO;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    Location createLocationDTOToEntity( CreateLocationDTO createLocationDTO);


    @Mapping(source = "location.meeting", target = "meeting", qualifiedByName = "toMeetingDTO")
    LocationDTO toDTO(Location location);

    @Named("toMeetingDTO")
    static List<MeetingDTO> toMeetingDTO(List<Meeting> meetings){
        return meetings.stream()
                .map(MeetingMapper.INSTANCE::toMeetingDTO)
                .collect(Collectors.toList());
    }


}
