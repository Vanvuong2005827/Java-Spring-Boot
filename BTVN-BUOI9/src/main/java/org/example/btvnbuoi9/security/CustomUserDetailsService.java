package org.example.btvnbuoi9.security;

import org.example.btvnbuoi9.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;
    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        org.example.btvnbuoi9.domain.entities.User u = userRepo.findByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + u.getRole().name())
        );

        return CustomUserDetails.builder()
                .id(u.getId())
                .username(u.getUsername())
                .password(u.getPassword())
                .authorities(authorities)
                .build();
    }
}
