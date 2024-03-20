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
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "totalmoney")
    private double totalMoney;

    @Column(name = "tradingcode")
    private String tradingCode;

    @Column(name = "createtime")
    private LocalDate createTime;

    @Column(name = "updatetime")
    private LocalDate updateTime;

    @Column(name = "customerid")
    private int customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "promotionid")
    private int promotionId;

    @Column(name = "billstatusid")
    private int billStatusId;

    @Column(name = "isactive")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "customerid", insertable = false, updatable = false)
    @JsonBackReference
    private User customer;

    @ManyToOne
    @JoinColumn(name = "promotionid",insertable = false, updatable = false)
    @JsonBackReference
    private Promotions promotion;


    @ManyToOne
    @JoinColumn(name = "billstatusid",insertable = false, updatable = false)
    @JsonBackReference
    private BillStatus billStatus;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "bill")
    @JsonManagedReference
    private List<BillFood> billFoods;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill")
    @JsonManagedReference
    private List<BillTickets> billTickets;
}
