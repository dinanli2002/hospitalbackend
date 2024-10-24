package com.example.introduccionbackend;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("nurses")
public class NurseController {
	
	@Autowired
	private NurseRepository nurseRepository; 
	
	
	@GetMapping("/username/{username}")
	public @ResponseBody Optional<Nurse> findByUsernme(@PathVariable("username") String username ) {
		// This returns a JSON or XML with the users
		return nurseRepository.findByUsername(username);
	}
	
}