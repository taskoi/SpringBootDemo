package com.webfactory.springbootdemo.demoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "location")
public class Location implements Serializable {

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

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<User> locationUsers = new ArrayList<>();

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<Post> locationPostsList = new ArrayList<>();

    public Location(){}

    public Location(@Min(-180) @Max(180) Float longitude, @Min(-90) @Max(90) Float latitude, @Size(max = 120) String city, @Size(max = 120) String country, List<User> locationUsers, List<Post> locationPostsList) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.country = country;
        this.locationUsers = locationUsers;
        this.locationPostsList = locationPostsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<User> getLocationUsers() {
        return locationUsers;
    }

    public void setLocationUsers(List<User> locationUsers) {
        this.locationUsers = locationUsers;
    }

    public List<Post> getLocationPostsList() {
        return locationPostsList;
    }

    public void setLocationPostsList(List<Post> locationPostsList) {
        this.locationPostsList = locationPostsList;
    }
}
