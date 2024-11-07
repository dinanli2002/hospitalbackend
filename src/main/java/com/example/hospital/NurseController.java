package  com.example.hospital;

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


@RestController
@RequestMapping("/nurses")

public class NurseController {


	@Autowired
	private NurseRepository nurseRepository;


	@GetMapping("/username/{username}")
	public @ResponseBody Optional<Nurse> findByUsernme(@PathVariable("username") String username ) {
		// This returns a JSON or XML with the users
		return nurseRepository.findByUsername(username);
	}

	@PostMapping("/create")
	public @ResponseBody ResponseEntity<String> createNurse(@RequestParam String username, @RequestParam String password){
    Optional<Nurse> existingNurse = nurseRepository.findByUsernameAndPassword(username, password);
    if (existingNurse.isPresent()) {
        return new ResponseEntity<>("Username and password already exist. Create failed.", HttpStatus.CONFLICT);
    }
    Nurse newNurse = new Nurse();
    newNurse.setUsername(username);
    newNurse.setPassword(password);
    nurseRepository.save(newNurse);
    return new ResponseEntity<>("Create nurse successful", HttpStatus.OK);
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


