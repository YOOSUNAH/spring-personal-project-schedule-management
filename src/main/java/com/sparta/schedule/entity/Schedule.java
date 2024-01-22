package com.sparta.schedule.entity;


import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor  // 기본 생성자
public class Schedule {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private int password;
    private int date;

    public Schedule(ScheduleRequestDto requestDto){
    this.title = requestDto.getTitle();
    this.contents = requestDto.getContents();
    this.manager = requestDto.getManager();
    this.password = requestDto.getPassword();
    this.date = requestDto.getDate();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }
}
