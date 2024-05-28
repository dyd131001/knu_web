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
@Schema(description = "메세지 생성 DTO")
public class CreateMessageDTO {

    @Schema(description = "메시지 내용", example = "안녕!")
    private String contents;

    @Schema(description = "사용자 ID", example = "1")
    private Long userId;

    @Schema(description = "회의 ID", example = "1")
    private Long meetingID;
}
