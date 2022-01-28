package com.bakery.bakeryProducts.controller;

import com.bakery.bakeryProducts.entity.User;
import com.bakery.bakeryProducts.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/userDetails")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user){
        return  userService.saveUser(user);
    }

    @PostMapping("/getUserDetails")
    public User getUserDetails(@RequestParam("userName") String userName){

        return  userService.getUserDetails(userName);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){

        return  userService.getAllUsers();
    }

    @PostMapping("/editUser")
    public String editUser(@RequestBody User user){

        return  userService.editUser(user.getUserId(),user.getUserRole().getRoleId(),user.getUsername(),user.getName(),
                                    user.isStatus());
    }

    @PostMapping("/searchUserById")
    public Optional<User> searchUserById(@RequestParam("userId") int userId){

        return  userService.searchUserById(userId);
    }

    @PostMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {

        return userService.logout(request, response);
    }

}
