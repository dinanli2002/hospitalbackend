package com.example.Introduccionbackend;

public class Nurse {


	private int id;
	private String username;
	private String password;

	public Nurse(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserame() {
		return name;
	}

	public void setUsername(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}



 
    
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
