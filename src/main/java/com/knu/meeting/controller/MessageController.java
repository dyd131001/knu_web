package com.knu.meeting.controller;

import com.knu.meeting.model.dto.CreateMessageDTO;
import com.knu.meeting.model.dto.GroupMessageDTO;
import com.knu.meeting.model.dto.UserDTO;
import com.knu.meeting.service.MessageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
@Transactional
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    //config에서 prefix를 /app 로 설정해서 /app/chat/1 로 메세지 보내야함.
    @MessageMapping("/chat/{meetingId}")
    @SendTo("/topic/meeting/{meetingId}")
    public GroupMessageDTO sendMessage(@DestinationVariable Long meetingId, CreateMessageDTO message) throws Exception {
        Thread.sleep(1000); // simulated delay

        return messageService.create(message);
    }
}
