package com.example.hospital;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nurses")

public class NurseController {

	@Autowired
	private NurseRepository nurseRepository;

	/*@GetMapping("/username/{username}")
	public @ResponseBody Optional<Nurse> findByUsernme(@PathVariable("username") String username) {
		// This returns a JSON or XML with the users
		return nurseRepository.findByUsername(username);
	}*/
	@GetMapping("/find/{id}")
	public @ResponseBody ResponseEntity<?> findNurseById(@PathVariable("id") int id) {
	    Optional<Nurse> existingNurse = nurseRepository.findById(id); 
	    if (!existingNurse.isPresent()) {    
	        return new ResponseEntity<>("Nurse with the specified ID not found.", HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<>(existingNurse.get(), HttpStatus.OK);
	}


	@PostMapping("/create")
	public @ResponseBody ResponseEntity<String> createNurse(@RequestParam String username,
			@RequestParam String password) {
		Optional<Nurse> existingNurse = nurseRepository.findByUsernameAndPassword(username, password);
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			return new ResponseEntity<>("Username and password cannot be empty.", HttpStatus.BAD_REQUEST);
		}
		if (existingNurse.isPresent()) {
			return new ResponseEntity<>("Username and password already exist. Create failed.", HttpStatus.CONFLICT);
		}
		Nurse newNurse = new Nurse();
		newNurse.setUsername(username);
		newNurse.setPassword(password);
		nurseRepository.save(newNurse);
		return new ResponseEntity<>("Create nurse successful", HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public @ResponseBody ResponseEntity<String> updateNurse(@RequestParam String username,
			@RequestParam String password, @PathVariable("id") int id) {
		
		Optional<Nurse> existingNurse = nurseRepository.findById(id);
		
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			return new ResponseEntity<>("Error with data sent", HttpStatus.BAD_REQUEST);
		}
		if (!existingNurse.isPresent()) {	
			return new ResponseEntity<>("Username and password don't exist. Update failed.", HttpStatus.NOT_FOUND);
		}
		
		
		existingNurse.get().setUsername(username);
		existingNurse.get().setPassword(password);
		nurseRepository.save(existingNurse.get());
		return new ResponseEntity<>("Update nurse successful", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody ResponseEntity<String> updateNurse(@PathVariable("id") int id) {
		
		Optional<Nurse> existingNurse = nurseRepository.findById(id);
		
		if (!existingNurse.isPresent()) {	
			return new ResponseEntity<>("Nurse not found. Delete failed.", HttpStatus.NOT_FOUND);
		}
		nurseRepository.delete(existingNurse.get());
		return new ResponseEntity<>("Delete nurse successful", HttpStatus.OK);
	}
	

	@PostMapping("/login")
	public @ResponseBody ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
		Optional<Nurse> existingNurse = nurseRepository.findByUsername(username);
		if (existingNurse.isEmpty()) {
			return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
		}
		if (existingNurse.get().getPassword().equals(password)) {
			return new ResponseEntity<>("Login successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid password.", HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/all")
	public @ResponseBody ResponseEntity<Iterable<Nurse>> getAll(){
		return new ResponseEntity<Iterable<Nurse>>(nurseRepository.findAll(),HttpStatus.OK);
	}
}
