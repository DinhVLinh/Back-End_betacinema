package com.example.beta.Repository;

import com.example.beta.Model.BillTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTicketRepository extends JpaRepository<BillTickets,Integer> {
}
