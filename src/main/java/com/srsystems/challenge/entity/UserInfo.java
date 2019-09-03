package com.srsystems.challenge.entity;

import lombok.Data;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;

@Data
@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
}
