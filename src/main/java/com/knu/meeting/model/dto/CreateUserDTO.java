package com.knu.meeting.model.dto;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.PossibleAge;
import com.knu.meeting.model.vo.TimeStatus;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 회원가입시 사용
@Getter
@Setter
@ToString
public class CreateUserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private TimeStatus timeStatus;
    private Address address;
    private int age;
    private String gender;
    private List<String> hobbies;

}
