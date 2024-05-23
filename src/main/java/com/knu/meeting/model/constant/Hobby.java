package com.knu.meeting.model.constant;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Hobby {
    CONVERSATION("대화"),
    GAME("게임"),
    DRINKING_GATHERING("술모임"),
    STUDY("공부"),
    BOWLING("볼링"),
    BILLIARDS("당구"),
    SINGING("노래"),
    STROLL("산책"),
    EMPTY("없음");

    String value;

    public static Hobby findHobby(String type){
        return Arrays.stream(Hobby.values())
                .filter(hobby -> hobby.getValue().equals(type))
                .findFirst()
                .orElse(EMPTY);
    }

}