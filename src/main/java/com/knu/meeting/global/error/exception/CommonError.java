package com.knu.meeting.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonError implements ErrorCode{
    INVALID_PARAMETER("C001", HttpStatus.BAD_REQUEST, "유효하지 않은 파라미터 입니다."),
    UNCHECKED_SERVER_ERROR("C002", HttpStatus.INTERNAL_SERVER_ERROR, "에러 확인이 필요합니다.");


    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
