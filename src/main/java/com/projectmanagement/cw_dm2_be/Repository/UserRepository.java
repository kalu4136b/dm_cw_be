package com.projectmanagement.cw_dm2_be.Repository;

import com.projectmanagement.cw_dm2_be.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Procedure(name = "User.addUser")
    void addUser(
            @Param("P_USERNAME") String username,
            @Param("P_PASSWORD") String password,
            @Param("P_EMAIL") String email,
            @Param("P_ROLE") String role
    );


    @Procedure(name = "User.updateUser")
    void updateUser(
            @Param("P_ID") Integer id,
            @Param("P_USERNAME") String username,
            @Param("P_PASSWORD") String password,
            @Param("P_EMAIL") String email,
            @Param("P_ROLE") String role
    );



    @Procedure(name = "User.deleteUser")
    void deleteUser(@Param("P_ID") Integer id);
}

