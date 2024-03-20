package com.example.beta.Repository;

import com.example.beta.Model.ConfirmEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmEmailRepo extends JpaRepository<ConfirmEmail,Integer> {
    ConfirmEmail findByConfirmCode(String code);
}
