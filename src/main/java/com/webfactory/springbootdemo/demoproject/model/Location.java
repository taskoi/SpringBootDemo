package com.webfactory.springbootdemo.demoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @Column(name = "longitude")
    @Min(value = -180, message = "minimum value on longitude is -180")
    @Max(value = 180, message = "maximum value on longitude is 180")
    @NotNull(message = "longitude cannot be null")
    private Float longitude;

    @Column(name = "latitude")
    @Min(value = -90, message = "minimum value on latitude is -90")
    @Max(value = 90, message = "maximum value on latitude is 90")
    @NotNull(message = "latitude cannot be null")
    private Float latitude;

    @Column(name = "city")
    @Size(max = 120, message = "maximum characters are 120")
    @NotNull(message = "city cannot be null")
    private String city;

    @Column(name = "country")
    @Size(max = 120, message = "maximum characters are 120")
    @NotNull(message = "country cannot be null")
    private String country;

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<User> locationUsers = new ArrayList<>();

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<Post> locationPostsList = new ArrayList<>();

    public Location() {
    }

    public Location(@Min(-180) @Max(180) Float longitude, @Min(-90) @Max(90) Float latitude, @Size(max = 120) String city, @Size(max = 120) String country, List<User> locationUsers, List<Post> locationPostsList) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.country = country;
        this.locationUsers = locationUsers;
        this.locationPostsList = locationPostsList;
    }

    public Location(@Min(value = -180, message = "minimum value on longitude is -180") @Max(value = 180, message = "maximum value on longitude is 180") @NotNull(message = "longitude cannot be null") Float longitude, @Min(value = -90, message = "minimum value on latitude is -90") @Max(value = 90, message = "maximum value on latitude is 90") @NotNull(message = "latitude cannot be null") Float latitude, @Size(max = 120, message = "maximum characters are 120") @NotNull(message = "city cannot be null") String city, @Size(max = 120, message = "maximum characters are 120") @NotNull(message = "country cannot be null") String country) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.country = country;
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
