package com.bakery.bakeryProducts.service;

import com.bakery.bakeryProducts.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    String saveUser(User user);
    User getUserDetails(String username);
    List<User> getAllUsers();
    String editUser(int userId,int roleId,String username,String name,boolean status);
    Optional<User> searchUserById(int userId);
}
