package com.example.introduccionbackend;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional; 
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/nurses")

public class NurseController {


	@Autowired
	private NurseRepository nurseRepository;


	//Error handling is missed, return status 404 not found in case no nurse found
	//And RESPONSE ENTITY
	
	@GetMapping("/username/{username}")
	public ResponseEntity<Nurse> findByUsernme(@PathVariable("username") String username) {
	    return nurseRepository.findByUsername(username)
	            .map(nurse -> ResponseEntity.ok(nurse)) 
	            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)); 
	}

	


	@PostMapping("/login")
	public @ResponseBody ResponseEntity<String> login(@RequestParam String username, @RequestParam String password){
    Optional<Nurse> existingNurse = nurseRepository.findByUsernameAndPassword(username, password);
    if (existingNurse.isPresent()) {
        return new ResponseEntity<>("Username and password already exist. Login failed.", HttpStatus.CONFLICT);
    }
    Nurse newNurse = new Nurse();
    newNurse.setUsername(username);
    newNurse.setPassword(password);
    nurseRepository.save(newNurse);
    return new ResponseEntity<>("Login successful", HttpStatus.OK);
	}
}

	

