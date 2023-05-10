package com.lab3.Laboratory3.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sites")
public class Sites {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "npp_name")
    private String nppName;

    @Column(name = "place")
    private int place;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "operator")
    private int operator;

    @Column(name = "builder")
    private int builder;
}
