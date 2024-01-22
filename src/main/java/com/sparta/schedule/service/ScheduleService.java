package com.sparta.schedule.service;

import com.sparta.schedule.Repository.ScheduleRepository;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

//    public ScheduleResponseDto getMemberInfo(Long scheduleId) {
//        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NullPointerException::new);
//        // Entity To DTO
//        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
//        return responseDto;
//    }
}
