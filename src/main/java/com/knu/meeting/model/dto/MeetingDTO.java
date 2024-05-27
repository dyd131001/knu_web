package com.knu.meeting.model.dto;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.vo.Position;
import com.knu.meeting.model.vo.PossibleAge;
import com.knu.meeting.model.vo.TimeStatus;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 초기 화면 세팅시 미팅 정보
@Getter
@Setter
@ToString
public class MeetingDTO {
    private Long id;
    private String locationName;

    private PossibleAge possibleAge;

    private TimeStatus timeStatus;

    private List<String> hobbies;

    private Position position;


}
