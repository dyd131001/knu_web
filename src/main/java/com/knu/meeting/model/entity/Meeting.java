package com.knu.meeting.model.entity;

import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.TimeStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
import lombok.Getter;
import org.hibernate.annotations.BatchSize;

@Entity
@Getter
@Table(name = "meetings" )
//@Table(name = "meetings" ,indexes = {@index(name="meeting_creator_id", columnList="creator_id"),
//       @index(name="meeting_creator_id", columnList="creator_id")
// })
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Embedded
    private Address address;

    private String location;

    @Embedded
    private TimeStatus timeStatus;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    //  @JoinColumn(name = "creator_id", foreignkey = @ForeignKey(ConstraintMode.NO_CONSTRAINNT))
    //  @JoinColumn(name = "creator_id", nullable= true)
    // 부모객체 삭제할때 자식객체 외래키를 null로 만들지 , 자식객체를 함께 삭제할지 선택
    private User creator;

    //CascadeType.REMOVE는 부모 엔티티가 삭제되면 자식 엔티티도 삭제
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "meeting", cascade = CascadeType.REMOVE)
    @BatchSize(size = 10)
    private Set<Participation> participations = new HashSet<Participation>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "meeting", cascade = CascadeType.REMOVE)
    @BatchSize(size = 10)
    private List<GroupMessage> messages = new ArrayList<GroupMessage>();

}
