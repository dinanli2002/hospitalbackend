package com.example.hospitalbackend;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.example.hospital.HospitalApplication;
import com.example.hospital.Nurse;
import com.example.hospital.NurseRepository;

@SpringBootTest(classes = { HospitalApplication.class })
class NurseControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@MockBean
	private NurseRepository nurseRepository;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void testFindByUsername_Success() throws Exception {
		Nurse nurse = new Nurse();
		nurse.setId(1);
		nurse.setUsername("nurse1");
		nurse.setPassword("password1");
		when(nurseRepository.findByUsername("nurse1")).thenReturn(Optional.of(nurse));
		mockMvc.perform(get("/nurses/username/nurse1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("nurse1"));
	}

	@Test
    void testFindByUsername_NotFound() throws Exception {
        when(nurseRepository.findByUsername("nurse1")).thenReturn(Optional.empty());
        mockMvc.perform(get("/nurses/username/nurse1"))
                .andExpect(status().isNotFound());
    }

	@Test
	void testFindById_Success() throws Exception {
		Nurse nurse = new Nurse();
		nurse.setId(1);
		nurse.setUsername("nurse1");
		nurse.setPassword("password1");
		when(nurseRepository.findById(1)).thenReturn(Optional.of(nurse));
		mockMvc.perform(get("/nurses/find/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1));
	}

	@Test
    void testFindById_NotFound() throws Exception {
        when(nurseRepository.findById(1)).thenReturn(Optional.empty());
        mockMvc.perform(get("/nurses/find/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Nurse with the specified ID not found."));
    }

	@Test
    void testCreateNurse_Success() throws Exception {
        when(nurseRepository.findByUsernameAndPassword("nurse1", "password1")).thenReturn(Optional.empty());
        mockMvc.perform(post("/nurses/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "nurse1")
                .param("password", "password1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Create nurse successful"));
    }

	@Test
	void testCreateNurse_Conflict() throws Exception {
		Nurse nurse = new Nurse();
		nurse.setId(1);
		nurse.setUsername("nurse1");
		nurse.setPassword("password1");
		when(nurseRepository.findByUsernameAndPassword("nurse1", "password1")).thenReturn(Optional.of(nurse));
		mockMvc.perform(post("/nurses/create").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("username", "nurse1").param("password", "password1")).andExpect(status().isConflict())
				.andExpect(content().string("Username and password already exist. Create failed."));
	}

	@Test
	void testCreateNurse_BadRequest() throws Exception {
		mockMvc.perform(post("/nurses/create").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", "")
				.param("password", "")).andExpect(status().isBadRequest())
				.andExpect(content().string("Username and password cannot be empty."));
	}

	@Test
	void testUpdateNurse_Success() throws Exception {
		Nurse nurse = new Nurse();
		nurse.setId(1);
		nurse.setUsername("nurse1");
		nurse.setPassword("password1");
		when(nurseRepository.findById(1)).thenReturn(Optional.of(nurse));
		mockMvc.perform(put("/nurses/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("username", "updatedNurse").param("password", "updatedPassword")).andExpect(status().isOk())
				.andExpect(content().string("Update nurse successful"));
	}

	@Test
    void testUpdateNurse_NotFound() throws Exception {
        when(nurseRepository.findById(1)).thenReturn(Optional.empty());
        mockMvc.perform(put("/nurses/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "updatedNurse")
                .param("password", "updatedPassword"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Username and password don't exist. Update failed."));
    }

	@Test
	void testUpdateNurse_BadRequest() throws Exception {
		mockMvc.perform(put("/nurses/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", "")
				.param("password", "")).andExpect(status().isBadRequest())
				.andExpect(content().string("Error with data sent"));
	}

	@Test
	void testDeleteNurse_Success() throws Exception {
		Nurse nurse = new Nurse();
		nurse.setId(1);
		nurse.setUsername("nurse1");
		nurse.setPassword("password1");
		when(nurseRepository.findById(1)).thenReturn(Optional.of(nurse));
		mockMvc.perform(delete("/nurses/delete/1")).andExpect(status().isOk())
				.andExpect(content().string("Delete nurse successful"));
	}

	@Test
    void testDeleteNurse_NotFound() throws Exception {
        when(nurseRepository.findById(1)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/nurses/delete/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Nurse not found. Delete failed."));
    }

	@Test
	void testLogin_Success() throws Exception {
		Nurse nurse = new Nurse();
		nurse.setId(1);
		nurse.setUsername("nurse1");
		nurse.setPassword("password1");
		when(nurseRepository.findByUsername("nurse1")).thenReturn(Optional.of(nurse));
		mockMvc.perform(post("/nurses/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("username", "nurse1").param("password", "password1")).andExpect(status().isOk())
				.andExpect(content().string("Login successful"));
	}

	@Test
    void testLogin_UserNotFound() throws Exception {
        when(nurseRepository.findByUsername("nurse1")).thenReturn(Optional.empty());
        mockMvc.perform(post("/nurses/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "nurse1")
                .param("password", "password1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found."));
    }

	@Test
	void testLogin_InvalidPassword() throws Exception {
		Nurse nurse = new Nurse();
		nurse.setId(1);
		nurse.setUsername("nurse1");
		nurse.setPassword("password1");
		when(nurseRepository.findByUsername("nurse1")).thenReturn(Optional.of(nurse));
		mockMvc.perform(post("/nurses/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("username", "nurse1").param("password", "wrongPassword")).andExpect(status().isUnauthorized())
				.andExpect(content().string("Invalid password."));
	}

	@Test
	void testGetAllNurses() throws Exception {
		Nurse nurse1 = new Nurse();
		nurse1.setId(1);
		nurse1.setUsername("nurse1");
		nurse1.setPassword("password1");
		Nurse nurse2 = new Nurse();
		nurse2.setId(2);
		nurse2.setUsername("nurse2");
		nurse2.setPassword("password2");
		when(nurseRepository.findAll()).thenReturn(Arrays.asList(nurse1, nurse2));
		mockMvc.perform(get("/nurses/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].username").value("nurse1"))
				.andExpect(jsonPath("$[1].username").value("nurse2"));
	}

}
