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
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "number")
    private int number;

    @Column(name = "seatstatusid")
    private int seatStatusId;

    @Column(name = "roomid")
    private int roomId;

    @Column(name = "isactive")
    private Boolean isActive;

    @Column(name = "seattypeid")
    private int seatTypeId;

    @Column(name = "line")
    private String line;

    @ManyToOne
    @JoinColumn(name = "seatstatusid", insertable = false, updatable = false)
    @JsonBackReference
    private SeatStatus seatStatus;

    @ManyToOne
    @JoinColumn(name = "roomid",insertable = false,updatable = false)
    @JsonBackReference
    private Room room;

    @ManyToOne
    @JoinColumn(name = "seattypeid",insertable = false,updatable = false)
    @JsonBackReference
    private SeatType seatType;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "seat")
    @JsonManagedReference
    private List<Tickets> tickets;

}
