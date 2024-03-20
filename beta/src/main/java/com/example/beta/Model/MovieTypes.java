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
public class MovieTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "movietypesname")
    private String movieTypesName;

    @Column(name = "isactive")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movieTypes")
    @JsonManagedReference
    private List<Movies> movies;
}
