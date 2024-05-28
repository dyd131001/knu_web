package com.knu.meeting.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 채팅방 입장시 사용
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "채팅방 접속시 필요한 정보 조회 DTO")
public class EnterMeetingDTO {

    @Schema(description = "회의 ID", example = "1")
    private Long id;

    @Schema(description = "회의 제목", example = "팀 회의")
    private String title;

    @Schema(description = "사용자 리스트")
    private List<UserDTO> users;

    @Schema(description = "메시지 리스트")
    private List<GroupMessageDTO> messages;

    @Schema(description = "위치 ID", example = "1")
    private Long locationId;


}
