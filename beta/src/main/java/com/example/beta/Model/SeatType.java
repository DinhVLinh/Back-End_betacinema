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
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nameType")
    private String nameType;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "seatType")
    @JsonManagedReference
    private List<Seat> seats;
}
