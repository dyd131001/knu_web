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
@Schema(description = "위치 정보")
public class Position {

    @Schema(description = "위도", example = "37.5665")
    private float lat;

    @Schema(description = "경도", example = "126.9780")
    private float lng;
}
