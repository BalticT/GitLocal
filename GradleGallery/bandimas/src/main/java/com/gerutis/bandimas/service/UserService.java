package com.gerutis.bandimas.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {



    public Boolean isAuthenticated() {

        return getAuthentication() != null && getAuthentication().isAuthenticated();
    }

    public Boolean isadmin () {

        return getAuthentication().getName().equals("admin");
    }

    public String getUsername() {

        return getAuthentication().getName();
    }

    public List<String > getRole() {
        return getAuthentication().getAuthorities()
                .stream()
                .map(authority -> ((GrantedAuthority) authority).getAuthority().toString())
                .collect(Collectors.toList());
    }

    private Authentication getAuthentication() {

        return SecurityContextHolder.getContext().getAuthentication();
    }
}
