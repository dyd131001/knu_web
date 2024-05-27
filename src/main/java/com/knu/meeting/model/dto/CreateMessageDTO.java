package com.knu.meeting.model.dto;

import com.knu.meeting.model.vo.TimeStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateMessageDTO {

    private String contents;

    private Long userId;
    private Long meetingID;
}
