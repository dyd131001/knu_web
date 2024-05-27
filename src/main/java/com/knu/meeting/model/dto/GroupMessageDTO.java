package com.knu.meeting.model.dto;

import com.knu.meeting.model.vo.TimeStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GroupMessageDTO {

    private Long id;

    private TimeStatus timeStatus;

    private String username;
    private String contents;
}
