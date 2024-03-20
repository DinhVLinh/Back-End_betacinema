package com.example.beta.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "billid")
    private int billId;

    @Column(name = "foodid")
    private int foodId;

    @ManyToOne
    @JoinColumn(name = "billid",insertable = false,updatable = false)
    @JsonBackReference
    private Bill bill;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "foodid",insertable = false,updatable = false)
    private Food food;

}
