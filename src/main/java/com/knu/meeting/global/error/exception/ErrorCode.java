package com.knu.meeting.global.error.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String name();
    String getCode();
    HttpStatus getHttpStatus();
    String getMessage();

}
