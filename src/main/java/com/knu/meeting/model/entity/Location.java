package com.knu.meeting.model.entity;

import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.Position;
import com.knu.meeting.model.vo.PossibleAge;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "locations" )
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "location", cascade = CascadeType.REMOVE)
    @BatchSize(size = 10)
    private List<Meeting> meeting;

    private String locationName;

    @Embedded
    private Position position;

    @Embedded
    private Address address;

    @Builder
    public Location(String locationName, Position position, Address address) {
        this.locationName = locationName;
        this.position = position;
        this.address = address;
        this.meeting = new ArrayList<Meeting>();
    }

    public void updateLocationName(String locationName){
        this.locationName = locationName;
    }

    public void updatePosition(Position position){
        this.position = position;
    }
}
