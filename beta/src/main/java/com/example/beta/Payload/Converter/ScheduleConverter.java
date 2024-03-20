package com.example.beta.Payload.Converter;

import com.example.beta.Model.Schedule;
import com.example.beta.Payload.DTO.ScheduleDto;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConverter {
    public ScheduleDto scheduleDto(Schedule schedule){
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setActive(schedule.getIsActive());
        scheduleDto.setCode(schedule.getCode());
        scheduleDto.setPrice(schedule.getPrice());
        scheduleDto.setStartAt(schedule.getStartAt());
        scheduleDto.setEndAt(schedule.getEndAt());
        scheduleDto.setName(schedule.getName());
        return scheduleDto;
    }
}
