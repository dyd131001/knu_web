package com.knu.meeting.loader;

import com.knu.meeting.model.dto.CreateMeetingDTO;
import com.knu.meeting.model.vo.PossibleAge;
import com.knu.meeting.service.MeetingService;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
@Order(3)
public class MeetingLoader implements ApplicationRunner {

    private final MeetingService meetingService;
    @Override
    public void run(ApplicationArguments args){
        CreateMeetingDTO meeting1 = new CreateMeetingDTO();
        meeting1.setTitle("팀 회의");
        meeting1.setPossibleAge(new PossibleAge(20, 30));
        meeting1.setHobbies(Arrays.asList("게임", "술모임"));
        meeting1.setLocationId(1L);
        meeting1.setUserId(1L);

        CreateMeetingDTO meeting2 = new CreateMeetingDTO();
        meeting2.setTitle("스포츠 모임");
        meeting2.setPossibleAge(new PossibleAge(25, 35));
        meeting2.setHobbies(Arrays.asList("볼링", "대화"));
        meeting2.setLocationId(2L);
        meeting2.setUserId(2L);

        // 데이터베이스에 삽입
        meetingService.create(meeting1);
        meetingService.create(meeting2);
    }
}
