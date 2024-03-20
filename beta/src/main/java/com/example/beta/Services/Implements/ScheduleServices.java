package com.example.beta.Services.Implements;

import com.example.beta.Model.Schedule;
import com.example.beta.Payload.Converter.ScheduleConverter;
import com.example.beta.Payload.DTO.ScheduleDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.ScheduleRepository;
import com.example.beta.Services.Interfaces.IScheduleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class ScheduleServices implements IScheduleServices {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleConverter scheduleConverter;
    @Autowired
    private ReponseObject scheduleReponse;

    @Override
    public ReponseObject<ScheduleDto> addSchedule(Schedule schedule) {
        Schedule newSchedule = Schedule.builder()
                .code(schedule.getCode())
                .endAt(schedule.getEndAt())
                .name(schedule.getName())
                .movieId(schedule.getMovieId())
                .roomId(schedule.getRoomId())
                .isActive(schedule.getIsActive())
                .price(schedule.getPrice())
                .build();
        scheduleRepository.save(newSchedule);
        return scheduleReponse.reponseSuccess("add movie successfully", scheduleConverter.scheduleDto(schedule));
    }

    @Override
    public ReponseObject<ScheduleDto> updateSchedule(Schedule schedule) {
        var scheduleUpdate = scheduleRepository.findById(schedule.getId());
        if(scheduleUpdate.isEmpty()){
            return scheduleReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"schedule not found",null);
        }
        scheduleUpdate.get().setName(schedule.getName());
        scheduleUpdate.get().setCode(schedule.getCode());
        scheduleUpdate.get().setPrice(schedule.getPrice());
        scheduleUpdate.get().setIsActive(schedule.getIsActive());
        scheduleUpdate.get().setMovieId(schedule.getMovieId());
        scheduleUpdate.get().setEndAt(schedule.getEndAt());
        scheduleUpdate.get().setStartAt(schedule.getStartAt());
        scheduleUpdate.get().setRoomId(schedule.getRoomId());
        scheduleRepository.save(scheduleUpdate.get());
        return scheduleReponse.reponseSuccess("update schedule successfully",scheduleConverter.scheduleDto(scheduleUpdate.get()));
    }

    @Override
    public ReponseObject<ScheduleDto> deleteSchedule(int id) {
        var schedule = scheduleRepository.findById(id);
        if(schedule.isEmpty()){
            return scheduleReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"schedule not found",null);
        }
        scheduleRepository.delete(schedule.get());
        return scheduleReponse.reponseSuccess("delete schedule successfully",scheduleConverter.scheduleDto(schedule.get()));
    }

    @Override
    public ReponseObject<ScheduleDto> getScheduleById(int id) {
        var schedule = scheduleRepository.findById(id);
        if(schedule.isEmpty()){
            return scheduleReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"schedule not found",null);
        }
        return scheduleReponse.reponseSuccess("get schedule successfully",scheduleConverter.scheduleDto(schedule.get()));
    }
}
