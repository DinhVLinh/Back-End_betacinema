package com.example.beta.Model;

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
public class SeatStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "namestatus")
    private String nameStatus;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "seatStatus")
    @JsonManagedReference
    private List<Seat> seats;
}
