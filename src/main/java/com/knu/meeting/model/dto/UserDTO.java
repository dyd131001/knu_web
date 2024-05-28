package com.knu.meeting.model.dto;

import com.knu.meeting.model.vo.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 간단한 유저 정보를 조회하기위한 DTO (ex: 상대방이 조회시)
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "기본 회원 정보 조회 DTO")
public class UserDTO {
    @Schema(description = "회원 id", defaultValue = "1"  /* allowableValues = { "", "" } */)
    private Long id;
    @Schema(description = "유저 이름", defaultValue = "dyd1310"  /* allowableValues = { "", "" } */)
    private String username;

    @Schema(description = "지역 분류", example = "{\"resion\":\"대구\", \"sity\" : \"북구\"}" /* allowableValues = { "", "" } */)
    private Address address;
    @Schema(description = "나이", defaultValue = "24"  /* allowableValues = { "", "" } */)
    private int age;
    @Schema(description = "성별", defaultValue = "남" , allowableValues = { "남", "여" } )
    private String gender;
    @Schema(description = "취미" ,example = "[\"게임\", \"술모임\"]", allowableValues = { "대화", "게임" ,"술모임", "공부", "볼링","당구","노래" , "산책"} )
    private List<String> hobbies;
}

