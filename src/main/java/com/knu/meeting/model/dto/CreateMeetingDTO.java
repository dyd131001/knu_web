package com.knu.meeting.model.dto;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.entity.GroupMessage;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.model.vo.PossibleAge;
import com.knu.meeting.model.vo.TimeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;


@Setter
@ToString
@Getter
@NoArgsConstructor
@Schema(description = "모임 생성 DTO")
public class CreateMeetingDTO {
    @Schema(description = "회의 제목", example = "팀 회의")
    private String title;

    @Schema(description = "가능한 연령대")
    private PossibleAge possibleAge;

    @Schema(description = "취미 리스트", example = "[\"게임\", \"술모임\"]")
    private List<String> hobbies;

    @Schema(description = "위치 ID", example = "1")
    private Long locationId;

    @Schema(description = "사용자 ID", example = "1")
    private Long userId;

}
