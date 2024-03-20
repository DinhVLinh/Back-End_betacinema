package com.example.beta.Services.Interfaces;

import com.example.beta.Model.BillTickets;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IBillTicketsServices {
    ReponseObject<BillTickets> addBillTicket(BillTickets billTickets);
    ReponseObject<BillTickets> updateBillTicket(BillTickets billTickets);
    ReponseObject<BillTickets> getBillTicketById(int id);
    ReponseObject<BillTickets> deleteBillTickets(int id);
}
