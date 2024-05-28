package com.knu.meeting.model.dto;

import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.TimeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//상세정보 조회시 사용
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "상세 회원 정보 조회 DTO")
public class DetailUserDTO {
    @Schema(description = "사용자 ID", example = "1")
    private Long id;

    @Schema(description = "사용자 이름", example = "dyd1310")
    private String username;

    @Schema(description = "이메일", example = "xx1310@example.com")
    private String email;

    @Schema(description = "시간 상태")
    private TimeStatus timeStatus;

    @Schema(description = "주소 정보",  example = "{\"resion\":\"대구\", \"sity\" : \"북구\"}" )
    private Address address;

    @Schema(description = "나이", example = "25")
    private int age;

    @Schema(description = "성별", example = "남", allowableValues = {"남", "여"})
    private String gender;

    @Schema(description = "취미 리스트", example = "[\"게임\", \"술모임\"]")
    private List<String> hobbies;

    @Schema(description = "회의 리스트")
    private List<MeetingDTO> meetings;
}
