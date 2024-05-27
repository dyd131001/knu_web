package com.knu.meeting.service;

import com.knu.meeting.mapper.GroupMessageMapper;
import com.knu.meeting.model.dto.CreateMessageDTO;
import com.knu.meeting.model.entity.GroupMessage;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.repository.MeetingRepository;
import com.knu.meeting.repository.MessageRepository;
import com.knu.meeting.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;
    private MeetingRepository meetingRepository;
    private UserRepository userRepository;

    public Long create(CreateMessageDTO createMessageDTO) {
        User user = userRepository.findById(createMessageDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("user not found with id: " + createMessageDTO.getUserId()));

        Meeting meeting = meetingRepository.findById(createMessageDTO.getMeetingID())
                .orElseThrow(() -> new EntityNotFoundException("location not found with id: " + createMessageDTO.getUserId()));

        GroupMessage groupMessage = GroupMessageMapper.INSTANCE.CreateMessageDTOToEntity(createMessageDTO,user,meeting);

        messageRepository.save(groupMessage);
        return groupMessage.getId();
    }


}
