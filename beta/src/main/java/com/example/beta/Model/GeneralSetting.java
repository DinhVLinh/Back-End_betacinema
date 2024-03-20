package com.example.beta.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "breaktime")
    private LocalDate breakTime;

    @Column(name = "bussinesshours")
    private int bussinessHours;

    @Column(name = "closehourse")
    private LocalDate closeTime;

    @Column(name = "fixedticketprice")
    private double fixedTicketPrice;

    @Column(name = "precentday")
    private int precentDay;

    @Column(name = "precentweekend")
    private int precentweekend;

    @Column(name = "timebegintochange")
    private LocalDate timeBeginToChange;
}
