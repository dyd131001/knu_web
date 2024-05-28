package com.knu.meeting.model.dto;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.PossibleAge;
import com.knu.meeting.model.vo.TimeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 회원가입시 사용
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "위치 생성 DTO")
public class CreateUserDTO {
    @Schema(description = "사용자 이름", example = "dyd1310")
    private String username;

    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @Schema(description = "이메일", example = "xx1310@example.com")
    private String email;

    @Schema(description = "주소 정보")
    private Address address;

    @Schema(description = "나이", example = "25")
    private int age;

    @Schema(description = "성별", example = "남", allowableValues = {"남", "여"})
    private String gender;

    @Schema(description = "취미 리스트", example = "[\"게임\", \"술모임\"]")
    private List<String> hobbies;

}
