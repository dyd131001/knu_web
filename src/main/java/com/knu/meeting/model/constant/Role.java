package com.knu.meeting.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    ANONYMOUS("ROLE_ANONYMOUS"),
    ADMIN("ROLE_ADMIN");

    String value;

}
