package com.project.authenticationservice.repository;

import com.project.authenticationservice.model.UserServiceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserServiceData,String> {
    UserServiceData findByUserNameAndPasswordAndRole(String userName, String password, String role);



}
