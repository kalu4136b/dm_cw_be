package com.projectmanagement.cw_dm2_be.Service;

import com.projectmanagement.cw_dm2_be.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projectmanagement.cw_dm2_be.Model.User;
//import com.projectmanagement.cw_dm2_be.Model.User as AppUser;





import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // com.projectmanagement.cw_dm2_be.Model.User appUser = userRepo.findByUsername(username)
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        /*return new org.springframework.security.core.userdetails.User(
                appUser.getUsername(),
                appUser.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(appUser.getRole()))
        );*/
    }
}