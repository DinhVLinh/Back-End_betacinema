package com.example.beta.Model;

import com.example.beta.Enumeration.RoleEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "rolename")
    private RoleEnum roleName;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role")
    @JsonManagedReference
    private List<User> users;


}
