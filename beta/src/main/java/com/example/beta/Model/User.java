package com.example.beta.Model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "point")
    private int point;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "rankcustomerid")
    private int rankCustomerId;

    @Column(name = "userstatusid")
    private int userStatusId;

    @Column(name = "isactive")
    private Boolean isActive;

    @Column(name = "roleid")
    private int roleId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "rankcustomerid",insertable = false,updatable = false)
    @JsonBackReference
    private RankCustomer rankCustomer;

    @ManyToOne
    @JoinColumn(name = "roleid",insertable = false,updatable = false)
    @JsonBackReference
    private Role role;

    @ManyToOne
    @JoinColumn(name = "userstatusid", insertable = false, updatable = false)
    @JsonBackReference
    private UserStatus userStatus;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
    @JsonManagedReference
    private List<Bill> bills;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    @JsonManagedReference
    private List<ConfirmEmail> confirmEmails;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    @JsonManagedReference
    private List<RefeshToken> refershTokens;




}
