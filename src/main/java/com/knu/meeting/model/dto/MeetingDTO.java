package com.knu.meeting.model.dto;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.vo.Position;
import com.knu.meeting.model.vo.PossibleAge;
import com.knu.meeting.model.vo.TimeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 초기 화면 세팅시 미팅 정보
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "모임 정보 조회 DTO")
public class MeetingDTO {
    @Schema(description = "회의 ID", example = "1")
    private Long id;

    @Schema(description = "회의 제목", example = "팀 회의")
    private String title;

    @Schema(description = "가능한 연령대")
    private PossibleAge possibleAge;

    @Schema(description = "시간 상태")
    private TimeStatus timeStatus;

    @Schema(description = "취미 리스트", example = "[\"게임\", \"술게임\"]")
    private List<String> hobbies;

}
