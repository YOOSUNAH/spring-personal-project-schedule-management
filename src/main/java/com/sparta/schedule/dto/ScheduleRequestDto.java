package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private int password;
    private int date;
}


