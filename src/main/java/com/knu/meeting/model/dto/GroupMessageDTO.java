package com.knu.meeting.model.dto;

import com.knu.meeting.model.vo.TimeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "메세지 정보 조회 DTO")
public class GroupMessageDTO {

    @Schema(description = "메시지 ID", example = "1")
    private Long id;

    @Schema(description = "시간 상태")
    private TimeStatus timeStatus;

    @Schema(description = "사용자 이름", example = "dyd1310")
    private String username;

    @Schema(description = "메시지 내용", example = "안녕!")
    private String contents;

}
