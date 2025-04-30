package com.projectmanagement.cw_dm2_be.Service;


import com.projectmanagement.cw_dm2_be.Model.User;
import com.projectmanagement.cw_dm2_be.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void addUser(String username, String password, String email, String role) {
        userRepository.addUser(username, password, email, role);
    }

    public void updateUser(int id, String username, String password, String email, String role) {
        userRepository.updateUser(id, username, password, email, role);
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public List<Object[]> getAllUsers() {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("GET_ALL_USERS")
                .registerStoredProcedureParameter("P_CURSOR", void.class, ParameterMode.REF_CURSOR);
        query.execute();
        return query.getResultList();
    }

    public Object[] getUserById(int id) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("GET_USER_BY_ID")
                .registerStoredProcedureParameter("P_ID", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("P_CURSOR", void.class, ParameterMode.REF_CURSOR);
        query.setParameter("P_ID", id);
        query.execute();
        List<Object[]> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
