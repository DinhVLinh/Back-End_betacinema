package com.example.beta.Repository;

import com.example.beta.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
