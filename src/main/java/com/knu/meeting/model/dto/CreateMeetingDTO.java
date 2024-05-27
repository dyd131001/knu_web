package com.knu.meeting.model.dto;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.entity.GroupMessage;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.model.vo.PossibleAge;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;


@Setter
@ToString
@Getter
public class CreateMeetingDTO {
    private String title;
    private PossibleAge possibleAge;
    private List<String> hobbies;

    private Long locationId;
    private Long userId;

}
