package com.knu.meeting.global.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDTO<T> {
    private final Boolean success;
    private final String code;
    private final String name;
    private final String message;
    private final T data;

    public static <T> ResponseDTO<T> of(T data , String message){
        return ResponseDTO.<T>builder()
                .success(true)
                .code("200")
                .name("OK")
                .message(message) // 수행 기능 적기
                .data(data)
                .build();
    }

    public static <T> ResponseDTO<T> of(T data){
        return ResponseDTO.<T>builder()
                .success(true)
                .code("200")
                .name("OK")
                .message("") // 수행 기능 적기
                .data(data)
                .build();
    }



}
