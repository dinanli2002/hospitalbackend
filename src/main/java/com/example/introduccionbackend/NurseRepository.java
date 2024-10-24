package com.example.introduccionbackend;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional; 

public interface NurseRepository extends CrudRepository<Nurse, Integer>{
	Optional<Nurse> findByUsername ( String username); 
}
