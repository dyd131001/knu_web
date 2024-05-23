package com.knu.meeting.model.dto;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.TimeStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserDTO {
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
