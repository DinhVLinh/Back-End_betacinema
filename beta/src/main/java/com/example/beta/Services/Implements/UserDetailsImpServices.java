package com.example.beta.Services.Implements;

import com.example.beta.Model.User;
import com.example.beta.Repository.UserRepository;
import com.example.beta.Ultis.UserCustomDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserDetailsImpServices implements UserDetailsService {
    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUserName(username);
        return UserCustomDetails.builder().user(user.get()).build();
    }
}
