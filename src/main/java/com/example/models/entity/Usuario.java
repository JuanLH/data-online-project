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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	public Usuario() {
	} 
	
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
		estado = 1;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
    @Size(max = 100)
    @Column(unique = true)
	private String nombre;
	
	@Null
	private String fullname;
	
	

	@NotEmpty
    @Size(min=4,max=50)
	private String clave;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-mm-yyyy")
	private Date bod;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-mm-yyyy")
	private Date createAt;
	
	@Null
	private String foto;
	
	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String correo;
	
	//@ManyToOne
	//@JoinColumn(name="letra")
	private char gender;
	
	@NotNull
	private int estado;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<GrupoDato> gruposDatos;
	
	@OneToMany(mappedBy="usuario", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Visita> visitas;
	// Agregar el metodo add
	
	public void addGrupoDato(GrupoDato grupoDato) {
		gruposDatos.add(grupoDato);
	}
	
	public void addVisita(Visita visita) {
		visitas.add(visita);
	}
	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getBod() {
		return bod;
	}

	public void setBod(Date bod) {
		this.bod = bod;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	

	public char getGender() {
		return gender;
	}


	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public List<GrupoDato> getGruposDatos() {
		return gruposDatos;
	}

	public void setGruposDatos(List<GrupoDato> gruposDatos) {
		this.gruposDatos = gruposDatos;
	}

	public List<Visita> getVisitas() {
		return visitas;
	}

	public void setVisitas(List<Visita> visitas) {
		this.visitas = visitas;
	}
	
	
	
}
