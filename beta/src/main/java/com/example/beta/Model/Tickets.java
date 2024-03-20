package com.example.beta.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "scheduleid")
    private int scheduleId;

    @Column(name = "seatid")
    private int seatId;

    @Column(name = "priceTicket")
    private double priceTicket;

    @Column(name = "isactive")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "ticket")
    @JsonManagedReference
    private List<BillTickets> billTickets;

    @ManyToOne
    @JoinColumn(name = "scheduleid",insertable = false,updatable = false)
    @JsonBackReference
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "seatid",insertable = false,updatable = false)
    @JsonBackReference
    private Seat seat;

}
