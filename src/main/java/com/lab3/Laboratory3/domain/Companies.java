package com.lab3.Laboratory3.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "companies")
public class Companies {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "companies_name")
    private String companiesName;

    @Column(name = "full_name")
    private String fullName;

    @JoinColumn(table = "countries", name = "id")
    private int countryId;
}