package com.example.beta.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userid")
    private int userId;

    @Column(name = "confirmcode")
    private String confirmCode;

    @Column(name = "isconfirm")
    private Boolean isConfirm;

    @Column(name = "requiredtime")
    private LocalDate requiredTime;

    @Column(name = "expiredtime")
    private LocalDate expiredTime;

    @ManyToOne
    @JoinColumn(name = "userid", insertable = false,updatable = false)
    private User user;

}
