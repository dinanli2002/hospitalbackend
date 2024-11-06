package com.example.hospital;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Integer>{
	Optional<Nurse> findByUsernameAndPassword(String username, String password);
    Optional<Nurse> findByUsername(String username);
  
}