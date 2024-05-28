package com.knu.meeting.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

@Embeddable
@Data
@NoArgsConstructor
@Schema(description = "시간 상태")
public class TimeStatus {

    @CreationTimestamp
    @Column(name="join_time", updatable = false)
    @Schema(description = "가입 시간", example = "2023-01-01T00:00:00")
    private LocalDateTime joinTime;

    @UpdateTimestamp
    @Column(name="mod_time")
    @Schema(description = "수정 시간", example = "2023-01-02T00:00:00")
    private LocalDateTime modifiedTime;
}
