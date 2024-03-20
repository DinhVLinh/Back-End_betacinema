package com.example.beta.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillTickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "billid")
    private int billid;

    @Column(name = "ticketid")
    private int ticketId;

    @ManyToOne
    @JoinColumn(name = "billid", insertable = false,updatable = false)
    @JsonBackReference
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "ticketid",insertable = false,updatable = false)
    @JsonBackReference
    private Tickets ticket;


}
