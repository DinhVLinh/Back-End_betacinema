package com.example.beta.Repository;

import com.example.beta.Model.RefeshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefeshTokenRepository extends JpaRepository<RefeshToken,Integer> {
}
