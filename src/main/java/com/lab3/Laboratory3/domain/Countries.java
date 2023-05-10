package com.lab3.Laboratory3.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "countries")
public class Countries {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "subregion")
    private String subregion;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "region_id")
    private int regionId;
}