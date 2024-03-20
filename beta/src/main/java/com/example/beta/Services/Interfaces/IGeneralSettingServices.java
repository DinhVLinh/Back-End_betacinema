package com.example.beta.Services.Interfaces;

import com.example.beta.Model.GeneralSetting;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IGeneralSettingServices {
    ReponseObject<GeneralSetting> addGenerateSetting(GeneralSetting generalSetting);
    ReponseObject<GeneralSetting> updateGenerateSetting(GeneralSetting generalSetting);
    ReponseObject<GeneralSetting> getGenerateSettingById(int id);
    ReponseObject<GeneralSetting> deleteGenerateSettingById(int id);
}
