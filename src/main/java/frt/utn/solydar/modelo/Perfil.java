package frt.utn.solydar.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Entity
@Table(name="Perfil")
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_perfil")
	private Long idPerfil;
	
	@Column(name="nombre_perfil")
	private String nombrePerfil;
	
	@ManyToMany(
			fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
			)
	@JoinTable(
			name = "usuario_perfil",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name="perfil_id")
			)
	private List<Usuario> usuarios;

	
	
	
	public Perfil(String nombrePerfil) {
		
		this.nombrePerfil = nombrePerfil;
	}

	public Perfil() {
		
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombrePerfil() {
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void agregarUsuario(Usuario usuario) {
		if(usuarios == null) {
			usuarios = new ArrayList<>();
		}
		usuarios.add(usuario);
		
	}

	@Override
	public String toString() {
		return "Perfil [idPerfil=" + idPerfil + ", nombrePerfil=" + nombrePerfil + ", usuarios=" + usuarios + "]";
	}

	
	
	
	
	
}
