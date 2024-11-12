package com.example.hospitalbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.hospital.Nurse;
import com.example.hospital.NurseController;
import com.example.hospital.NurseRepository;

public class NurseControllerTest {

	@Mock
	private NurseRepository nurseRepository;

	@InjectMocks
	private NurseController nurseController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindNurseById_Success() {
		Nurse nurse = new Nurse();
		nurse.setId(1);
		nurse.setUsername("testUser");
		nurse.setPassword("testPassword");
		when(nurseRepository.findById(1)).thenReturn(Optional.of(nurse));
		ResponseEntity<?> response = nurseController.findNurseById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(nurse, response.getBody());
	}

	@Test
    public void testFindNurseById_NotFound() {
        when(nurseRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<?> response = nurseController.findNurseById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Nurse with the specified ID not found.", response.getBody());
    }

	@Test
    public void testCreateNurse_Success() {
        when(nurseRepository.findByUsernameAndPassword("newUser", "newPass")).thenReturn(Optional.empty());
        ResponseEntity<String> response = nurseController.createNurse("newUser", "newPass");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Create nurse successful", response.getBody());
        verify(nurseRepository, times(1)).save(any(Nurse.class));
    }

	@Test
	public void testCreateNurse_Conflict() {
		Nurse existingNurse = new Nurse();
		when(nurseRepository.findByUsernameAndPassword("existingUser", "existingPass"))
				.thenReturn(Optional.of(existingNurse));
		ResponseEntity<String> response = nurseController.createNurse("existingUser", "existingPass");
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		assertEquals("Username and password already exist. Create failed.", response.getBody());
	}

	@Test
	public void testUpdateNurse_Success() {
		Nurse nurse = new Nurse();
		nurse.setId(1);
		nurse.setUsername("oldUser");
		nurse.setPassword("oldPass");
		when(nurseRepository.findById(1)).thenReturn(Optional.of(nurse));
		ResponseEntity<String> response = nurseController.updateNurse("newUser", "newPass", 1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Update nurse successful", response.getBody());
		verify(nurseRepository, times(1)).save(any(Nurse.class));
	}

	@Test
    public void testUpdateNurse_NotFound() {
        when(nurseRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<String> response = nurseController.updateNurse("newUser", "newPass", 1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Username and password don't exist. Update failed.", response.getBody());
    }

	@Test
	public void testDeleteNurse_Success() {
		Nurse nurse = new Nurse();
		nurse.setId(1);
		when(nurseRepository.findById(1)).thenReturn(Optional.of(nurse));
		ResponseEntity<String> response = nurseController.updateNurse(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Delete nurse successful", response.getBody());
		verify(nurseRepository, times(1)).delete(nurse);
	}

	@Test
    public void testDeleteNurse_NotFound() {
        when(nurseRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<String> response = nurseController.updateNurse(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Nurse not found. Delete failed.", response.getBody());
    }

	@Test
	public void testLogin_Success() {
		Nurse nurse = new Nurse();
		nurse.setUsername("user1");
		nurse.setPassword("pass1");
		when(nurseRepository.findByUsername("user1")).thenReturn(Optional.of(nurse));
		ResponseEntity<String> response = nurseController.login("user1", "pass1");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Login successful", response.getBody());
	}

	@Test
	public void testLogin_InvalidPassword() {
		Nurse nurse = new Nurse();
		nurse.setUsername("user1");
		nurse.setPassword("correctPass");
		when(nurseRepository.findByUsername("user1")).thenReturn(Optional.of(nurse));
		ResponseEntity<String> response = nurseController.login("user1", "wrongPass");
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertEquals("Invalid password.", response.getBody());
	}

	@Test
    public void testLogin_UserNotFound() {
        when(nurseRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());
        ResponseEntity<String> response = nurseController.login("nonExistentUser", "anyPass");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found.", response.getBody());
    }
}
