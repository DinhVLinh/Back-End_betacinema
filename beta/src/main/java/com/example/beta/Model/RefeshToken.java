package com.example.beta.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefeshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "token")
    private String token;

    @Column(name = "expiredtime")
    private LocalDate expiredTime;

    @Column(name = "usedid")
    private int userId;

    @ManyToOne
    @JoinColumn(name="userid",insertable = false , updatable = false)
    @JsonBackReference
    private User user;
}
