package com.example.authorization.repositories;

import com.example.authorization.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {



    Users save(Users entity);

    Optional<Users> findByEmail(String email);

    Optional<Users> findByEmailAndAndPassword(String email,String password);
}
