package com.knu.meeting.model.entity;

import com.knu.meeting.model.constant.Gender;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.constant.Role;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.Position;
import com.knu.meeting.model.vo.PossibleAge;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Entity
@Getter
@Table(name = "meetings" )
@RequiredArgsConstructor
//@Table(name = "meetings" ,indexes = {@index(name="meeting_creator_id", columnList="creator_id"),
//       @index(name="meeting_creator_id", columnList="creator_id")
// })
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Embedded
    private PossibleAge possibleAge;

    @Embedded
    private TimeStatus timeStatus;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    //  @JoinColumn(name = "creator_id", foreignkey = @ForeignKey(ConstraintMode.NO_CONSTRAINNT))
    //  @JoinColumn(name = "creator_id", nullable= true)
    // 부모객체 삭제할때 자식객체 외래키를 null로 만들지 , 자식객체를 함께 삭제할지 선택
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @ElementCollection(targetClass = Hobby.class)
    // 해당 collection 측 외래키 이름 = joinColumns
    @CollectionTable(name = "meeting_hobbies", joinColumns = @JoinColumn(name = "meeting_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "hobby")
    private List<Hobby> hobbies;

    //CascadeType.REMOVE는 부모 엔티티가 삭제되면 자식 엔티티도 삭제
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "meeting", cascade = CascadeType.REMOVE)
    @BatchSize(size = 10)
    private List<Participation> participations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "meeting", cascade = CascadeType.REMOVE)
    @BatchSize(size = 10)
    private List<GroupMessage> messages;

    @Builder
    public Meeting(String title, PossibleAge possibleAge,List<Hobby> hobbies , Location location , User creator) {
        this.title = title;
        this.possibleAge = possibleAge;
        this.location = location;
        this.timeStatus = new TimeStatus();
        this.creator = creator;
        this.participations = new ArrayList<Participation>();
        this.messages = new ArrayList<GroupMessage>();
        this.hobbies = hobbies != null ? hobbies : new ArrayList<>();
        location.getMeeting().add(this);
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateLocation(Location location) {
        if (this.location !=  null) {
            this.location.getMeeting().remove(this);
        }
        this.location = location;
        location.getMeeting().add(this);
    }

    public void updatePossibleAge(PossibleAge possibleAge) {
        this.possibleAge = possibleAge;
    }

    public void updateHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }



}
