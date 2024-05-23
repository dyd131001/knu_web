package com.knu.meeting.model.vo;

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
public class TimeStatus {
    @CreationTimestamp
    @Column(name="join_time", updatable = false)
    private LocalDateTime joinTime;

    @UpdateTimestamp
    @Column(name="mod_time")
    private LocalDateTime modifiedTime;
}
