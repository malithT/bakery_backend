package com.bakery.bakeryProducts.controller;
import com.bakery.bakeryProducts.entity.UserRole;
import com.bakery.bakeryProducts.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userRoleDetails")
@AllArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;


    @PostMapping("/saveUserRole")
    public String saveUser(@RequestBody UserRole userRole){
        return userRoleService.saveUserRole(userRole);
    }

    @GetMapping("/getAllUserRoles")
    public List<UserRole> GetAllUserRoles(){
        return userRoleService.getAllUsersRoles();
    }

    @PostMapping("/editUserRole")
    public String editUserRole(@RequestBody UserRole userRole){
        return userRoleService.editUserRole(userRole.getRoleId(),userRole.getRoleName());
    }

    @PostMapping("/searchUserRoleById")
    public Optional<UserRole> searchUserRoleById(@RequestParam ("roleId") int roleId){
        return userRoleService.searchUserRoleById(roleId);
    }


}
