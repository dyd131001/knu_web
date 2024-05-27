package com.knu.meeting.model.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 채팅방 입장시 사용
@Getter
@Setter
@ToString
public class EnterMeetingDTO {

    private Long id;

    private String title;

    private List<UserDTO> Users ;
    private List<GroupMessageDTO> Messages ;

    private Long locationId;


}
