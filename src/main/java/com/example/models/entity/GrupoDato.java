package com.example.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "grupos_datos")
public class GrupoDato implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	public GrupoDato() {
	}
	
  
	public GrupoDato(@NotNull @Size(min = 3, max = 30) String grupo, @NotNull String descripcion) {
		this.grupo = grupo;
		this.descripcion = descripcion;
	}


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;
	
	@NotNull
    @Size(min=3,max =30)
	private String grupo;
	
	@NotNull
	private String descripcion;
	
	
	
	@OneToMany(mappedBy = "grupoDato",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Dato> datos;
	
	@OneToMany(mappedBy = "grupoDato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<GrupoCompartido> gruposCompartidos;
	
	public void addDato(Dato dato) {
		datos.add(dato);
	}
	
	public void addGrupoCompartido(GrupoCompartido grupoCompartido) {
		gruposCompartidos.add(grupoCompartido);
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Dato> getDatos() {
		return datos;
	}

	public void setDatos(List<Dato> datos) {
		this.datos = datos;
	}

	public List<GrupoCompartido> getGruposCompartidos() {
		return gruposCompartidos;
	}

	public void setGruposCompartidos(List<GrupoCompartido> gruposCompartidos) {
		this.gruposCompartidos = gruposCompartidos;
	}


	
	
	
	
	
	
	
	
}
