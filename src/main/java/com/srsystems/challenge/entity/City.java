package com.srsystems.challenge.entity;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.function.Function;

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

    private City(Long id) {
        this.id = id;
    }

    public static City of(Long id) {
        return new City(id);
    }

    public interface Square {
        int calculate(int x);
    }

    public static void main(String[] args) {
        Square f = (int x)->x*x;
        System.out.println(f.calculate(5));
    }
}
