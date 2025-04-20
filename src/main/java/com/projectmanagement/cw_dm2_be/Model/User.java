package com.projectmanagement.cw_dm2_be.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="`Users`")
public class User {

    @Id
    private int id;
    private String username;
    private String password;
    private String role;



    }



