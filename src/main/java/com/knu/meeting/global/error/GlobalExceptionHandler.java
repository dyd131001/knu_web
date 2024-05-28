package com.knu.meeting.global.error;

import com.knu.meeting.global.common.response.ErrorResponseDTO;
import com.knu.meeting.global.error.exception.ApiException;
import com.knu.meeting.global.error.exception.CommonError;
import com.knu.meeting.global.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleCustomException(ApiException e){
        ErrorCode errorCode = e.getErrorCode();
        return handleExecptionInternal(errorCode);
    }

    private ResponseEntity<Object> handleExecptionInternal(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponseDTO.of(errorCode));
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.warn("handleIllegalArgument", e);
        ErrorCode errorCode = CommonError.INVALID_PARAMETER;
        return handleExceptionInternal(e,errorCode);
    }

    private ResponseEntity<Object> handleExceptionInternal(MethodArgumentNotValidException e, ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponseDTO.of(e, errorCode));
    }



    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception ex) {
        log.warn("handleAllException", ex);
        ErrorCode errorCode = CommonError.UNCHECKED_SERVER_ERROR;
        return handleExecptionInternal(errorCode);
    }



}
