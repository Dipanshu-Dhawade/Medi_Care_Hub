package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.enums.Role;
import com.Hospital_Management_System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

  public User findByUsernameAndPassword(String username , String password);

  public  User  findByUsername(String username);

  @Query("""
    SELECT u.role.role
    FROM User u
    WHERE u.id = :userId
""")
  Role findUserRole(@Param("userId") Long userId);

}