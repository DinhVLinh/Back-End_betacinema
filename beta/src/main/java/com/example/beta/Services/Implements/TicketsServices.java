package com.example.beta.Services.Implements;

import com.example.beta.Model.Tickets;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.TicketsRepository;
import com.example.beta.Services.Interfaces.ITicketsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class TicketsServices implements ITicketsServices {
    @Autowired
    private TicketsRepository ticketsRepository;
    @Autowired
    private ReponseObject ticketsReponse;
    @Override
    public ReponseObject<Tickets> addTickets(Tickets tickets) {
        Tickets ticketsNew = Tickets.builder()
                .code(tickets.getCode())
                .scheduleId(tickets.getScheduleId())
                .seatId(tickets.getSeatId())
                .priceTicket(tickets.getPriceTicket())
                .isActive(tickets.getIsActive())
                .build();
        ticketsRepository.save(ticketsNew);
        return ticketsReponse.reponseSuccess("add ticket successfully",ticketsNew);
    }

    @Override
    public ReponseObject<Tickets> updateTickets(Tickets tickets) {
        var ticketsUpdate = ticketsRepository.findById(tickets.getId());
        if(ticketsUpdate.isEmpty()){
            return ticketsReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"update tickets successfully",null);
        }
        ticketsUpdate.get().setCode(tickets.getCode());
        ticketsUpdate.get().setScheduleId(tickets.getScheduleId());
        ticketsUpdate.get().setSeatId(tickets.getSeatId());
        ticketsUpdate.get().setPriceTicket(tickets.getPriceTicket());
        ticketsUpdate.get().setIsActive(tickets.getIsActive());
        ticketsRepository.save(ticketsUpdate.get());
        return ticketsReponse.reponseSuccess("update ticket successfully",ticketsUpdate.get());
    }

    @Override
    public ReponseObject<Tickets> getTicketsByid(int id) {
        var tickets = ticketsRepository.findById(id);
        if(tickets.isEmpty()){
            return ticketsReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"update tickets successfully",null);
        }
        return ticketsReponse.reponseSuccess("get ticket successfully",tickets.get());
    }

    @Override
    public ReponseObject<Tickets> deleteTickets(int id) {
        var tickets = ticketsRepository.findById(id);
        if(tickets.isEmpty()){
            return ticketsReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"update tickets successfully",null);
        }
        ticketsRepository.delete(tickets.get());
        return ticketsReponse.reponseSuccess("delete ticket successfully",tickets.get());
    }
}
