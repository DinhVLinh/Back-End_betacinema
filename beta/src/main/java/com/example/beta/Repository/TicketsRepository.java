package com.example.beta.Repository;

import com.example.beta.Model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets,Integer> {
}
