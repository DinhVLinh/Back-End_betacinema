package com.example.beta.Model;

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
public class Promotions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "percent")
    private int percent;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "type")
    private int type;

    @Column(name = "endtime")
    private LocalDate endTime;

    @Column(name = "starttime")
    private LocalDate startTime;

    @Column(name = "description")
    private String descsription;

    @Column(name = "name")
    private String name;

    @Column(name = "isactive")
    private Boolean isActive;

    @Column(name = "rankcustomerid")
    private int rankCustomerId;


    @ManyToOne
    @JoinColumn(name = "rankcustomerid", insertable = false,updatable = false)
    private RankCustomer rankCustomer;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "promotion")
    @JsonManagedReference
    private List<Bill> bills;


}
