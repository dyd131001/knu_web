package com.knu.meeting.model.dto;

import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.TimeStatus;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//상세정보 조회시 사용
@Getter
@Setter
@ToString
public class DetailUserDTO {
    private Long id;
    private String username;
    private String email;
    private TimeStatus timeStatus;
    private Address address;
    private int age;
    private String gender;
    private List<String> hobbies;
    private List<MeetingDTO> meetings;
}
