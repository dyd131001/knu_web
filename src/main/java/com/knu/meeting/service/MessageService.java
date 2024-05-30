package com.knu.meeting.service;

import com.knu.meeting.mapper.GroupMessageMapper;
import com.knu.meeting.model.dto.CreateMessageDTO;
import com.knu.meeting.model.dto.GroupMessageDTO;
import com.knu.meeting.model.entity.GroupMessage;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.repository.MeetingRepository;
import com.knu.meeting.repository.MessageRepository;
import com.knu.meeting.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class MessageService {

    private MessageRepository messageRepository;
    private MeetingRepository meetingRepository;
    private UserRepository userRepository;

    public GroupMessageDTO create(CreateMessageDTO createMessageDTO) {
        User user = userRepository.findById(createMessageDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("user not found with id: " + createMessageDTO.getUserId()));

        Meeting meeting = meetingRepository.findById(createMessageDTO.getMeetingID())
                .orElseThrow(() -> new EntityNotFoundException("Meeting not found with id: " + createMessageDTO.getMeetingID()));

        GroupMessage groupMessage = GroupMessageMapper.INSTANCE.CreateMessageDTOToEntity(createMessageDTO,user,meeting);

        messageRepository.save(groupMessage);

        return GroupMessageMapper.INSTANCE.toDTO(groupMessage);
    }


}
