/*package com.projectmanagement.cw_dm2_be.Service;

import com.projectmanagement.cw_dm2_be.Model.User;
import com.projectmanagement.cw_dm2_be.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                appUser.getUsername(),
                appUser.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(appUser.getRole()))
        );
    }

    // Extra service method to save a user
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}*/

package com.projectmanagement.cw_dm2_be.Service;

import com.projectmanagement.cw_dm2_be.Repository.UserRepository;
import com.projectmanagement.cw_dm2_be.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User User = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));


        return new org.springframework.security.core.userdetails.User(
                User.getUsername(),
                User.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(User.getRole()))
        );

    }
    public User saveUser(User user) {
        return userRepo.save(user);
    }
}

