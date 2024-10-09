package com.example.Introduccionbackend;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/nurse")

public class NurseController {
	
	    @GetMapping("/all")
	    public List<Nurse> getAllNurses() {
	        
	        Nurse nurse1 = new Nurse(0, "Dayanna Bonilla", "password123");
	        Nurse nurse2 = new Nurse(1, "Daniel Rios", "password123"); 
	        Nurse nurse3 = new Nurse(2, "Dinan", "password1");

	        return Arrays.asList(nurse1, nurse2, nurse3);
	   }

}
