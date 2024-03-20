package com.example.beta.Services.Implements;

import com.example.beta.Model.Bill;
import com.example.beta.Model.BillTickets;
import com.example.beta.Payload.DTO.BillDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.BillFoodRepository;
import com.example.beta.Repository.BillTicketRepository;
import com.example.beta.Services.Interfaces.IBillServices;
import com.example.beta.Services.Interfaces.IBillTicketsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class BillTicketsServices implements IBillTicketsServices {
    @Autowired
    private BillTicketRepository billTicketRepository;
    @Autowired
    private ReponseObject billTicketReponse;

    @Override
    public ReponseObject<BillTickets> addBillTicket(BillTickets billTickets) {
        var billTicket = BillTickets.builder()
                .quantity(billTickets.getQuantity())
                .billid(billTickets.getBillid())
                .ticketId(billTickets.getTicketId())
                .build();
        billTicketRepository.save(billTicket);
        return billTicketReponse.reponseSuccess("add bill ticket successfully", billTicket);
    }

    @Override
    public ReponseObject<BillTickets> updateBillTicket(BillTickets billTickets) {
        var billTicketUpdate = billTicketRepository.findById(billTickets.getId());
        if (billTicketUpdate.isEmpty()) {
            return billTicketReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "bill ticket not found", null);
        }
        billTicketUpdate.get().setQuantity(billTickets.getQuantity());
        billTicketUpdate.get().setTicketId(billTickets.getTicketId());
        billTicketUpdate.get().setBillid(billTickets.getBillid());
        billTicketRepository.save(billTicketUpdate.get());
        return billTicketReponse.reponseSuccess("update bill ticket successfully", billTicketUpdate.get());
    }

    @Override
    public ReponseObject<BillTickets> getBillTicketById(int id) {
        var billTicket = billTicketRepository.findById(id);
        if (billTicket.isEmpty()) {
            return billTicketReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "bill ticket not found", null);
        }

        return billTicketReponse.reponseSuccess("get bill tickets successfully", billTicket.get());
    }

    @Override
    public ReponseObject<BillTickets> deleteBillTickets(int id) {
        var billTicket = billTicketRepository.findById(id);
        if (billTicket.isEmpty()) {
            return billTicketReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "bill ticket not found", null);
        }
        billTicketRepository.delete(billTicket.get());
        return billTicketReponse.reponseSuccess("delete bill tickets successfully", billTicket.get());
    }
}
