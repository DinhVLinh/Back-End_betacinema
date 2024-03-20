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
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String desccription;

    @Column(name = "image")
    private String image;

    @Column(name = "nameoffood")
    private String nameOfFood;

    @Column(name = "isactive")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "food")
    @JsonManagedReference
    private List<BillFood> billFoods;

}
