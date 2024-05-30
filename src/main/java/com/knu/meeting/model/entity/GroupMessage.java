package com.knu.meeting.model.entity;


import com.knu.meeting.model.vo.TimeStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;

@Entity
@Getter
@Table(name = "massages")
@RequiredArgsConstructor
public class GroupMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private TimeStatus timeStatus;


    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    private String contents;

    @Builder
    public GroupMessage( User creator, Meeting meeting, String contents) {
        this.creator = creator;
        this.timeStatus = new TimeStatus();
        this.meeting = meeting;
        this.contents = contents;
        Hibernate.initialize(meeting);
        meeting.getMessages().add(this);
    }


}
