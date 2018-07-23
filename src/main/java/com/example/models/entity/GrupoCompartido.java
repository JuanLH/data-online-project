package com.example.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grupos_compartidos")
public class GrupoCompartido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public GrupoCompartido() {
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Visita visita;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private GrupoDato grupoDato;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public GrupoDato getGrupoDato() {
		return grupoDato;
	}

	public void setGrupoDato(GrupoDato grupoDato) {
		this.grupoDato = grupoDato;
	}
	
	
}
