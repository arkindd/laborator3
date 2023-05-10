package com.lab3.Laboratory3.domain;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "regions")
public class Region {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "region_name")
    private String regionName;
}