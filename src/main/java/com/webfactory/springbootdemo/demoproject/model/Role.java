//package com.webfactory.springbootdemo.demoproject.model;
//
//
//import io.swagger.annotations.ApiModelProperty;
//import org.codehaus.jackson.annotate.JsonIgnore;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
//@Entity
//@Table(name = "roles")
//public class Role implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String role;
//
//    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @ApiModelProperty(hidden = true)
//    private List<User> users = new ArrayList<>();
//
//    public Role() {
//
//    }
//
//    public Role(String r) {
//        this.role = r;
//    }
//
//    public Role(String role, List<User> users) {
//        this.role = role;
//        this.users = users;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//}
