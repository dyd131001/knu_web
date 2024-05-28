package com.knu.meeting.global.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.knu.meeting.global.error.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

/*
    예시

    throw new ApiException(ClientError.INVALID_PARAMETER);
 */
@Getter
@Builder
@RequiredArgsConstructor
@Schema(description = "에러 응답 형식")
public class ErrorResponseDTO {
    @Schema(description = "성공 여부", defaultValue = "true"  /* allowableValues = { "", "" } */)
    private final boolean sucess;
    @Schema(description = "에러 분류", defaultValue = "C001")
    private final String code;
    @Schema(description = "에러 이름", defaultValue = "USER_CONFLICT_ERROR"  /* allowableValues = { "", "" } */)
    private final String name;
    @Schema(description = "에러 메세지", defaultValue = "중복된 회원이 존재합니다."  /* allowableValues = { "", "" } */)
    private final String message;
    @Schema(description = "validation 오류 발생시 error list", nullable = true /* allowableValues = { "", "" } */)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ValidationError> errors;

    public static ErrorResponseDTO of(ErrorCode errorCode){
        return ErrorResponseDTO.builder()
                .sucess(false)
                .code(errorCode.getCode())
                .name(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }

    public static ErrorResponseDTO of(MethodArgumentNotValidException e, ErrorCode errorCode){
        List<ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponseDTO.builder()
                .sucess(false)
                .code(errorCode.getCode())
                .name(errorCode.name())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    @Schema(description = "validation 에러 응답 형식")
    public static class ValidationError {
        @Schema(description = "validation error 발생 필드", defaultValue = "password"  /* allowableValues = { "", "" } */)
        private final String field;
        @Schema(description = "validation error 발생 원인", defaultValue = "대문자를 포함할 수 없습니다."  /* allowableValues = { "", "" } */)
        private final String message;

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }

}
