package com.bakery.bakeryProducts.service;

import com.bakery.bakeryProducts.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    String saveUser(User user);
    User getUserDetails(String username);
    List<User> getAllUsers();
    String editUser(int userId,int roleId,String username,String name,boolean status);
    Optional<User> searchUserById(int userId);
    String logout(HttpServletRequest request, HttpServletResponse response);
}
