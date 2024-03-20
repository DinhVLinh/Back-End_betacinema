package com.example.beta.Services.Implements;

import com.example.beta.Model.GeneralSetting;
import com.example.beta.Payload.Reponse.ReponseObject;

import com.example.beta.Repository.GeneralSettingRepository;
import com.example.beta.Services.Interfaces.IGeneralSettingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class GeneralSettingServices implements IGeneralSettingServices {
    @Autowired
    private GeneralSettingRepository generalSettingRepository;

    @Autowired
    private ReponseObject generalSettingReponse;
    @Override
    public ReponseObject<GeneralSetting> addGenerateSetting(GeneralSetting generalSetting) {
        GeneralSetting generalSettingNew = GeneralSetting.builder()
                .breakTime(generalSetting.getBreakTime())
                .bussinessHours(generalSetting.getBussinessHours())
                .closeTime(generalSetting.getCloseTime())
                .fixedTicketPrice(generalSetting.getFixedTicketPrice())
                .precentweekend(generalSetting.getPrecentweekend())
                .precentDay(generalSetting.getPrecentDay())
                .timeBeginToChange(generalSetting.getTimeBeginToChange())
                .build();
        generalSettingRepository.save(generalSettingNew);
        return generalSettingReponse.reponseSuccess("add general setting successfully",generalSettingNew);
    }

    @Override
    public ReponseObject<GeneralSetting> updateGenerateSetting(GeneralSetting generalSetting) {

        var generalSettingUpdate = generalSettingRepository.findById(generalSetting.getId()) ;
        if(generalSettingUpdate.isEmpty()){
            return generalSettingReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"general setting not found",null);
        }
        generalSettingUpdate.get().setBreakTime(generalSetting.getBreakTime());
        generalSettingUpdate.get().setCloseTime(generalSetting.getCloseTime());
        generalSettingUpdate.get().setBussinessHours(generalSetting.getBussinessHours());
        generalSettingUpdate.get().setFixedTicketPrice(generalSetting.getFixedTicketPrice());
        generalSettingUpdate.get().setPrecentDay(generalSetting.getPrecentDay());
        generalSettingUpdate.get().setPrecentweekend(generalSetting.getPrecentweekend());
        generalSettingUpdate.get().setTimeBeginToChange(generalSetting.getTimeBeginToChange());
        generalSettingRepository.save(generalSettingUpdate.get());
        return generalSettingReponse.reponseSuccess("update general setting successfully",generalSettingUpdate.get());
    }

    @Override
    public ReponseObject<GeneralSetting> getGenerateSettingById(int id) {
        var generaSetting = generalSettingRepository.findById(id);
        if(generaSetting.isEmpty()){
            return generalSettingReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"general setting not found",null);
        }

        return generalSettingReponse.reponseSuccess("get general setting by id successfully",generaSetting.get());
    }

    @Override
    public ReponseObject<GeneralSetting> deleteGenerateSettingById(int id) {
        var generaSetting = generalSettingRepository.findById(id);
        if(generaSetting.isEmpty()){
            return generalSettingReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"general setting not found",null);
        }
        generalSettingRepository.delete(generaSetting.get());
        return generalSettingReponse.reponseSuccess("get general setting by id successfully",generaSetting.get());
    }
}
