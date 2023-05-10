package com.lab3.Laboratory3.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "units")
public class Units {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "site")
    private int site;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "model")
    private String model;

    @Column(name = "class")
    private String unitClass;

    @Column(name = "ru_design")
    private boolean ruDesign;

    @Column(name = "operator")
    private int operator;

    @Column(name = "nsss_suplier")
    private int nsssSuplier;

    @Column(name = "thermal_capacity")
    private int thermalCapacity;

    @Column(name = "gross_capacity")
    private int grossCapacity;

    @Column(name = "net_capacity")
    private int netCapacity;

    @Column(name = "construction_start")
    private Date constructionStart;

    @Column(name = "commercial_operation")
    private Date commercialOperation;

    @Column(name = "date_shutdown")
    private Date dateShutdown;

    @Column(name = "enrichment")
    private double enrichment;

    @Column(name = "load_factor")
    private int loadFactor;

    @Column(name = "burnup")
    private double burnup;

    @Column(name = "volumeOfYearConsume")
    private double volumeOfYearConsume;
}
