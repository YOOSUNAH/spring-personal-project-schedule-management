package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private String title;
    private String contents;
    private String manager;
    private int date;


    public ScheduleResponseDto(Schedule schedule) {
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.manager = schedule.getManager();
        this.date = schedule.getDate();
    }

    public ScheduleResponseDto(String title, String contents, String manager, int date) {
    }
}
