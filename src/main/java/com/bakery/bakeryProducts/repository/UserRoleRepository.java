package com.bakery.bakeryProducts.repository;

import com.bakery.bakeryProducts.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value ="UPDATE `user_role` " +
            "SET role_name=:roleName where role_id=:roleId")
    void updateUserRole(int roleId,String roleName);

}
