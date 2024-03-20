package com.example.beta.Services.Interfaces;

import com.example.beta.Model.Schedule;
import com.example.beta.Payload.DTO.ScheduleDto;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IScheduleServices {
    ReponseObject<ScheduleDto> addSchedule(Schedule schedule);
    ReponseObject<ScheduleDto> updateSchedule(Schedule schedule);
    ReponseObject<ScheduleDto> deleteSchedule(int id);

    ReponseObject<ScheduleDto> getScheduleById(int id);
}
