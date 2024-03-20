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
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private String code;

    @Column(name = "nameofcinema")
    private String nameOfCinema;

    @Column(name = "isActive")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cinema")
    @JsonManagedReference
    private List<Room> rooms;
}
