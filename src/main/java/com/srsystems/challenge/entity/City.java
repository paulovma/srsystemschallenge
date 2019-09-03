package com.srsystems.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long ibgeId;

    @Column(length = 100, nullable = false)
    private String name;

    @ManyToOne
    private State state;

    @Column(nullable = false)
    private Boolean capital;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    private String accent;

    private String alternativeNames;

    private String microRegion;

    private String mesoRegion;
}
