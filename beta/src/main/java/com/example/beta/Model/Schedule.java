package com.example.beta.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "price")
    private double price;

    @Column(name = "startat")
    private LocalDate startAt;

    @Column(name = "endat")
    private LocalDate endAt;

    @Column(name = "code")
    private String code;

    @Column(name = "movieid")
    private int movieId;

    @Column(name = "name")
    private String name;

    @Column(name = "roomid")
    private int roomId;

    @Column(name = "isactive")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "roomid",insertable = false,updatable = false)
    @JsonBackReference
    private Room room;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule")
    @JsonManagedReference
    private List<Tickets> tickets;
}
