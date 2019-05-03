package com.webfactory.springbootdemo.demoproject.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "longitude", nullable = false)
    @Min(-180)
    @Max(180)
    private Float longitude;

    @Column(name = "latitude", nullable = false)
    @Min(-90)
    @Max(90)
    private Float latitude;

    @Column(name = "city", nullable = false)
    @Size(max = 120)
    private String city;

    @Column(name = "country", nullable = false)
    @Size(max = 120)
    private String country;
}
