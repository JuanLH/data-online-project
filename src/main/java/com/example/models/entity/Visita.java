package com.example.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "visitas")
public class Visita implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Visita() {
	}
	
	@PrePersist
	public void prePersist() {
		estado = 1;//disponible 
	}
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;
	
	@OneToOne
	private Usuario usuarioVisitante;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="fecha_visita")
	private Date fechaVisita;
	
	@NotNull
	@Column(unique=true)
	private String clave;
	
	@Null
	private String mensaje;
	
	@NotNull
	private int estado;
	
	@OneToMany(mappedBy = "visita", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<GrupoCompartido> gruposCompartidos;
	
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

	public Usuario getUsuarioVisitante() {
		return usuarioVisitante;
	}

	public void setUsuarioVisitante(Usuario usuarioVisitante) {
		this.usuarioVisitante = usuarioVisitante;
	}

	public Date getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public List<GrupoCompartido> getGruposCompartidos() {
		return gruposCompartidos;
	}

	public void setGruposCompartidos(List<GrupoCompartido> gruposCompartidos) {
		this.gruposCompartidos = gruposCompartidos;
	}
	
	
}
