package com.knu.meeting.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LocationError implements ErrorCode{
    LOCATION_CONFLICT_ERROR("L001", HttpStatus.CONFLICT, "중복된 위치가 존재합니다.");


    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
