package com.knu.meeting.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "가능한 연령대")
public class PossibleAge {

    @Schema(description = "시작 나이", example = "20")
    private int startAge;

    @Schema(description = "종료 나이", example = "30")
    private int endAge;
}