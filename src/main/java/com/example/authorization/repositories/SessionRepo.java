package com.example.authorization.repositories;

import com.example.authorization.models.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Sessions,Long> {


    Sessions save(Sessions entity);

    void deleteByToken(String token);

    @Override
    void delete(Sessions entity);

    Sessions findByToken(String token);
}
