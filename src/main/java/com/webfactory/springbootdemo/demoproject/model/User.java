package com.webfactory.springbootdemo.demoproject.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "mm_user")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email
    @Size(max = 120)
    @Column(name = "email", unique = true)
    private String email;

    //na krajot treba da se proveri dali treba .*$ ili .+$
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    @Size(min = 8, max = 120)
    @Column(name = "password")
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "firstName")
    @Size(max = 20)
    private String firstName;

    @Column(name = "lastName")
    @Size(max = 20)
    private String lastName;

    @Column(name = "username")
    @NotEmpty
    private String username;

    //defining user posts
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> userPostsList;

    //defining user location
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location")
    private Location location;



    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


    public User() {
    }

    public User(String email,String username, String password, String nickname, String firstName, String lastName,Location location) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getUserPostsList() {
        return userPostsList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserPostsList(List<Post> userPostsList) {
        this.userPostsList = userPostsList;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
