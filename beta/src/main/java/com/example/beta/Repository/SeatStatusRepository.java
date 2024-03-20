package com.example.beta.Repository;

import com.example.beta.Model.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatStatusRepository extends JpaRepository<SeatStatus,Integer> {
}
