package com.example.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gender")
public class Gender {

	
	public Gender(String nombre, char letra) {
		this.nombre = nombre;
		this.letra = letra;
	}
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Id
	private char letra;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public char getLetra() {
		return letra;
	}
	public void setLetra(char letra) {
		this.letra = letra;
	}
	
	
	
}
