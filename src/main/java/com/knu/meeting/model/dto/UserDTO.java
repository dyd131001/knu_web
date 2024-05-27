package com.knu.meeting.model.dto;

import com.knu.meeting.model.vo.Address;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 간단한 유저 정보를 조회하기위한 DTO (ex: 상대방이 조회시)
@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private String username;
    private Address address;
    private int age;
    private String gender;
    private List<String> hobbies;
}
