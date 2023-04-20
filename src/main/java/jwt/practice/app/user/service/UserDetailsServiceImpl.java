package jwt.practice.app.user.service;

import jwt.practice.app.user.domain.UserDetails;
import jwt.practice.app.user.repository.UserRepository;
import jwt.practice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .map(user -> new UserDetails(user, Collections.singleton(new SimpleGrantedAuthority(user.getRole().getValue()))))
                .orElseThrow(() -> new UserNotFoundException(userEmail));
    }
}
