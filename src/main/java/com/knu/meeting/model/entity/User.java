package com.knu.meeting.model.entity;
import com.knu.meeting.model.constant.Gender;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.constant.Role;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.TimeStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    //VO는 수정시 객체를 다시만들어야함
    @Embedded
    private TimeStatus timeStatus;

    @Embedded
    private Address address;

    private int age;
    private Gender gender;
    private Role role;

    @ElementCollection(targetClass = Hobby.class)
    // 해당 collection 측 외래키 이름 = joinColumns
    @CollectionTable(name = "user_hobbies", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "hobby")
    private List<Hobby> hobbies;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Participation> participations;

    @Builder
    public User(String username, String password, String email, Address address, int age, Gender gender, Role role , List<Hobby> hobbies) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.timeStatus = new TimeStatus();
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.hobbies = hobbies != null ? hobbies : new ArrayList<>();
        this.participations = new ArrayList<>();
    }

    public void updateAddress(Address address) {
        this.address = address;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }


}
