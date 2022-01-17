package com.bakery.bakeryProducts.service.impl;

import com.bakery.bakeryProducts.entity.UserRole;
import com.bakery.bakeryProducts.repository.UserRoleRepository;
import com.bakery.bakeryProducts.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Override
    public String saveUserRole(UserRole userRole) {
        JSONObject alert = new JSONObject();
        userRoleRepository.save(userRole);
        alert.put("message","User Role Created Successfully");
        return alert.toString();
    }

    @Override
    public List<UserRole> getAllUsersRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    public String editUserRole(int roleId, String roleName) {
        JSONObject alert = new JSONObject();
        userRoleRepository.updateUserRole(roleId,roleName);
        alert.put("message","User Role Updated Successfully");
        return alert.toString();
    }

    @Override
    public Optional<UserRole> searchUserRoleById(int roleId) {
        return userRoleRepository.findById(roleId);
    }

}
