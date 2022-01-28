package com.bakery.bakeryProducts.service.impl;


import com.auth0.jwt.JWT;
import com.bakery.bakeryProducts.entity.User;
import com.bakery.bakeryProducts.entity.UserRole;
import com.bakery.bakeryProducts.repository.UserRepository;
import com.bakery.bakeryProducts.repository.UserRoleRepository;
import com.bakery.bakeryProducts.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.net.httpserver.Authenticator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessEventPublishingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean status = true;
        User user = userRepository.findByUsernameAndStatus(username, status);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        } else {
            log.info("User Found");
        }
        Collection<UserRole> userRoles = new ArrayList<>();
        userRoles.add(user.getUserRole());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public String saveUser(User user) {
        JSONObject alert = new JSONObject();
        try {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserRole(userRoleRepository.getById(user.getUserRole().getRoleId()));
            userRepository.save(user);
            alert.put("message", "User Created Successfully");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return alert.toString();
    }

    @Override
    public User getUserDetails(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String editUser(int userId, int roleId, String username, String name, boolean status) {
        JSONObject alert = new JSONObject();
        userRepository.editUser(userId, roleId, username, name, status);
        alert.put("message", "User Updated Successfully");
        return alert.toString();
    }

    @Override
    public Optional<User> searchUserById(int userId) {
        try {
            return userRepository.findById(userId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return userRepository.findById(userId);
    }

    @Override
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        JSONObject alert = new JSONObject();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            auth.setAuthenticated(false);
            alert.put("message","User logged out successfully");
        }
        return alert.toString();
    }


}
