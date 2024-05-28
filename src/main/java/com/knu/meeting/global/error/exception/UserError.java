package com.knu.meeting.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserError implements ErrorCode{
    USER_CONFLICT_ERROR("U001", HttpStatus.CONFLICT, "중복된 회원이 존재합니다.");


    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
