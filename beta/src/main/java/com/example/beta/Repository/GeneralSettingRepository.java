package com.example.beta.Repository;

import com.example.beta.Model.GeneralSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralSettingRepository extends JpaRepository<GeneralSetting,Integer> {
}
