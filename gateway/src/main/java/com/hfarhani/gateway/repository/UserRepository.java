package com.hfarhani.gateway.repository;

import com.hfarhani.gateway.model.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<JpaUser, Integer> {

    @Query("SELECT u FROM  User u WHERE u.username =:username")
    public JpaUser findUserByUsername(@Param("username") String username);

}
