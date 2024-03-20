package com.example.beta.Services.Interfaces;

import com.example.beta.Model.Tickets;
import com.example.beta.Payload.Reponse.ReponseObject;


public interface ITicketsServices {
    ReponseObject<Tickets> addTickets(Tickets tickets);
    ReponseObject<Tickets> updateTickets(Tickets tickets);
    ReponseObject<Tickets> getTicketsByid(int id);
    ReponseObject<Tickets> deleteTickets(int id);
}
