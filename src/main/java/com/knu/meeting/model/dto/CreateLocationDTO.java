package com.knu.meeting.model.dto;

import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.Position;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "회원 생성 DTO")
public class CreateLocationDTO {

    @Schema(description = "위치 이름", example = "Central Park")
    private String locationName;

    @Schema(description = "위치 정보")
    private Position position;

    @Schema(description = "주소 정보")
    private Address address;

}
