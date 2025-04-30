package com.projectmanagement.cw_dm2_be.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "User.addUser",
                procedureName = "ADD_USER",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USERNAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PASSWORD", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_EMAIL", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ROLE", type = String.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "User.updateUser",
                procedureName = "UPDATE_USER",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USERNAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PASSWORD", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_EMAIL", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ROLE", type = String.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "User.deleteUser",
                procedureName = "DELETE_USER",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID", type = Integer.class)
                }
        )
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String email;
    private String role;
}
