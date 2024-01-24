package com.ismail.grading.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ismail.grading.entity.User;
import com.ismail.grading.service.UserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private UserService userServiceImpl;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String incomingUsername = authentication.getName();
        User user = userServiceImpl.getUser(incomingUsername);
        String incomingPass = authentication.getCredentials().toString();
        String storedPass = user.getPassword();
        if (!bCryptPasswordEncoder.matches(incomingPass, storedPass)) {
            throw new BadCredentialsException("Wrong password!");
        }

        return new UsernamePasswordAuthenticationToken(incomingUsername, storedPass);
    }
}
