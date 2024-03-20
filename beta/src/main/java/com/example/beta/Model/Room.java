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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "type")
    private int type;

    @Column(name = "description")
    private String desctiption;

    @Column(name = "cinemaid")
    private int cinemaId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "isactive")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "cinemaid", insertable = false, updatable = false)
    @JsonBackReference
    private Cinema cinema;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "room")
    @JsonManagedReference
    private List<Schedule> schedules;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "room")
    @JsonManagedReference
    private List<Seat> seats;


}
