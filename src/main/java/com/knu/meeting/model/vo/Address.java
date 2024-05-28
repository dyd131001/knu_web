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
@Schema(description = "주소 정보")
public class Address {

    @Schema(description = "시", example = "서울특별시")
    private String resion;

    @Schema(description = "구", example = "강남구")
    private String city;


}
