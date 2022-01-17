package com.bakery.bakeryProducts.service;
import com.bakery.bakeryProducts.entity.User;
import com.bakery.bakeryProducts.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    String saveUserRole(UserRole userRole);
    List<UserRole> getAllUsersRoles();
    String editUserRole(int roleId,String roleName);
    Optional<UserRole> searchUserRoleById(int roleId);

}
