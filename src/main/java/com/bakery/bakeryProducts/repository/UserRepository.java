package com.bakery.bakeryProducts.repository;

import com.bakery.bakeryProducts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsernameAndStatus(String username,boolean status);

    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value ="UPDATE `user` " +
            "SET username=:username,role_id = :roleId,`name` = :name,status = :status  where user_id=:userId")
    void editUser(int userId,int roleId,String username,String name,boolean status);

    @Query(nativeQuery = true,value = "select count(user_id) from user where username = :username")
    int recordCount(String username);
}
