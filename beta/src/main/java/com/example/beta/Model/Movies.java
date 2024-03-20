package com.example.beta.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cache.annotation.CacheConfig;

import java.time.LocalDate;
import java.util.Locale;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "movieduration")
    private int movieDuration;

    @Column(name = "endtime")
    private LocalDate endTime;

    @Column(name = "premieredate")
    private LocalDate premiereDate;

    @Column(name = "description")
    private String description;

    @Column(name = "directior")
    private String directior;

    @Column(name = "imagge")
    private String image;

    @Column(name = "heroimage")
    private String heroImage;

    @Column(name = "language")
    private String language;

    @Column(name = "movietypeid")
    private int movieTypeId;

    @Column(name = "name")
    private String name;

    @Column(name = "rateid")
    private int rateid;

    @Column(name = "trailer")
    private String trailer;

    @Column(name = "isactive")
    private Boolean isActive;


    @ManyToOne
    @JoinColumn(name = "movietypeid", insertable = false, updatable = false )
    @JsonBackReference
    private MovieTypes movieTypes;

    @ManyToOne
    @JoinColumn(name = "rateid", insertable = false,updatable = false)
    @JsonBackReference
    private Rate rate;

}
