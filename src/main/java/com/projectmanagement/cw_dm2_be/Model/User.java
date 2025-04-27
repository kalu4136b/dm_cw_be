package com.projectmanagement.cw_dm2_be.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

//@Data
@Entity
@Getter
@Setter
@Table(name="`Users`")
//@AllArgsConstructor
//@NoArgsConstructor
public class User {

    @Id
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    }



