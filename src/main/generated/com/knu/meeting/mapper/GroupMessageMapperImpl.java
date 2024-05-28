package com.knu.meeting.mapper;

import com.knu.meeting.model.dto.CreateMessageDTO;
import com.knu.meeting.model.dto.GroupMessageDTO;
import com.knu.meeting.model.entity.GroupMessage;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-28T13:32:53+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class GroupMessageMapperImpl implements GroupMessageMapper {

    @Override
    public GroupMessage CreateMessageDTOToEntity(CreateMessageDTO createMessageDTO, User user, Meeting meeting) {
        if ( createMessageDTO == null && user == null && meeting == null ) {
            return null;
        }

        GroupMessage.GroupMessageBuilder groupMessage = GroupMessage.builder();

        if ( createMessageDTO != null ) {
            groupMessage.contents( createMessageDTO.getContents() );
        }
        if ( meeting != null ) {
            groupMessage.creator( meeting.getCreator() );
        }

        return groupMessage.build();
    }

    @Override
    public GroupMessageDTO toDTO(GroupMessage groupMessage) {
        if ( groupMessage == null ) {
            return null;
        }

        GroupMessageDTO groupMessageDTO = new GroupMessageDTO();

        groupMessageDTO.setUsername( groupMessageCreatorUsername( groupMessage ) );
        groupMessageDTO.setId( groupMessage.getId() );
        groupMessageDTO.setTimeStatus( groupMessage.getTimeStatus() );
        groupMessageDTO.setContents( groupMessage.getContents() );

        return groupMessageDTO;
    }

    private String groupMessageCreatorUsername(GroupMessage groupMessage) {
        if ( groupMessage == null ) {
            return null;
        }
        User creator = groupMessage.getCreator();
        if ( creator == null ) {
            return null;
        }
        String username = creator.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
