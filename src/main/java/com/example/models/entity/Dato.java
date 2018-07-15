package com.example.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "datos")
public class Dato implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Dato() {
	}


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@PrePersist
	public void prePersist() {
		fechaRegistro = new Date();
	}

	@ManyToOne(fetch=FetchType.LAZY)
	private GrupoDato grupoDato;
	
	@NotNull
	private String data;
	
	@NotNull
	private String contenido;
	
	@NotNull
	private String descripcion;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="fecha_registro")
	private  Date fechaRegistro;
	
	
	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public GrupoDato getGrupoDato() {
		return grupoDato;
	}


	public void setGrupoDato(GrupoDato grupoDato) {
		this.grupoDato = grupoDato;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getContenido() {
		return contenido;
	}


	public void setContenido(String contenido) {
		this.contenido = contenido;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Date getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public String toString() {
		return "Data:"+this.getData()+",Contenido:"+this.getContenido()+",Descripcion:"+this.getDescripcion();
	}
	
	
}
