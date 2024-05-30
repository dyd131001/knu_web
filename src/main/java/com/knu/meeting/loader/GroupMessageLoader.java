package com.knu.meeting.loader;

        import com.knu.meeting.model.dto.CreateMessageDTO;
        import com.knu.meeting.service.MessageService;
        import lombok.AllArgsConstructor;
        import org.springframework.boot.ApplicationArguments;
        import org.springframework.boot.ApplicationRunner;
        import org.springframework.core.annotation.Order;
        import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Order(4)
public class GroupMessageLoader implements ApplicationRunner {

    private final MessageService messageService;
    @Override
    public void run(ApplicationArguments args){
        CreateMessageDTO message1 = new CreateMessageDTO();
        message1.setContents("안녕!");
        message1.setUserId(1L);
        message1.setMeetingID(1L);

        CreateMessageDTO message2 = new CreateMessageDTO();
        message2.setContents("만나서 반가워요!");
        message2.setUserId(2L);
        message2.setMeetingID(2L);

        // 데이터베이스에 삽입
        messageService.create(message1);
        messageService.create(message2);

    }
}
