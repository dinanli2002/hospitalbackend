package com.example.Introduccionbackend;

import java.util.ArrayList;

import org.springframework.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NurseController {
	
	private ArrayList<Nurse> nurses = new ArrayList<>(); 
	public NurseController() {
		nurses.add(new Nurse(1,"Paciente1","123"));
		nurses.add(new Nurse(2,"Paciente2","321"));
		nurses.add(new Nurse(3,"Paciente3","456"));
		nurses.add(new Nurse(4,"Paciente4","654"));
	}
	
	@GetMapping("/name/{name}")
	private ResponseEntity<Nurse> findByName(@PathVariable String name){
		
		for (Nurse nurse : nurses) {
			System.out.println(nurse);
			if (name.equals(nurse.getName())) {
				return ResponseEntity.ok(nurse);
						
			}
			
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
}
