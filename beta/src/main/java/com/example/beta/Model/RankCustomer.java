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
public class RankCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "point")
    private int point;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "isactive")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rankCustomer")
    @JsonManagedReference
    private List<Promotions> promotions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rankCustomer")
    @JsonManagedReference
    private List<User> users;
}
