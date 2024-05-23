package com.knu.meeting.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("남"),
    FEMALE("여");

    String gender;
}
