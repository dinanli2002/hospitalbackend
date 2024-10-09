package com.example.Introduccionbackend;

public class Nurse {
	private int id;
    private String nombre;
    private String password;
    
    
    public Nurse() {
    }
    
    // Constructor
    public Nurse( int id, String nombre, String password) {
    	this.id = id; 
        this.nombre = nombre;
        this.password = password;
    }

    // Getters y setters
    
    public int getId() {
    	return id; 
    }
    
    public void setId() {
    this.id =id; 
    
    }
  
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    // Getter y Setter de password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
