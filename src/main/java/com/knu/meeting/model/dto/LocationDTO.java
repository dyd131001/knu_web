package com.knu.meeting.model.dto;

import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.Position;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

@Getter
@Setter
@ToString
public class LocationDTO {
    private Long id;

    private List<MeetingDTO> meeting;

    private String locationName;

    @Embedded
    private Position position;

    @Embedded
    private Address address;
}
