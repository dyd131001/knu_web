package com.knu.meeting.model.entity;

import com.knu.meeting.model.constant.Gender;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.constant.Role;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.TimeStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


// user와 meeting을 연결해주는 역할
@Entity
@Getter
@Table(name = "participations")
@RequiredArgsConstructor
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Embedded
    @Column(name = "time_status")
    private TimeStatus timeStatus;

    @Builder
    public Participation( User user, Meeting meeting) {
        this.user = user;
        this.meeting = meeting;
        user.getParticipations().add(this);
        meeting.getParticipations().add(this);
    }

//    public void  setUser(User user){
//        this.user = user;
//        user.getParticipations().add(this);
//    }
//
//    public void  setMeeting(Meeting meeting){
//        this.meeting = meeting;
//        meeting.getParticipations().add(this);
//
//    }

}

