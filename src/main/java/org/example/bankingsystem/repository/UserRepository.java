package org.example.bankingsystem.repository;

import org.example.bankingsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
//    public User findById(int id);
    User findByEmail(String email);
}
