/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.utn.solydar.modelo;

import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Mariano
 */

@Entity
@Table(name="Ciudadano")
public class Ciudadano {
        
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_ciudadano")
    private Long idCiudadano;
   
    @Column(name="nombre_ciudadano")
    private String nombreCiudadano;
   
    @Column(name="telefono_ciudadano")
    private String telefono;
    
    @Column(name="perfil_face_ciudadano")
    private String perfilFace;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    public Ciudadano() {
    }

    public Ciudadano(String nombreCiudadano, String telefono, String perfilFace) {
        this.nombreCiudadano = nombreCiudadano;
        this.telefono = telefono;
        this.perfilFace = perfilFace;
        
        
    }

    public Long getIdCiudadano() {
        return idCiudadano;
    }

    public void setIdCiudadano(Long idCiudadano) {
        this.idCiudadano = idCiudadano;
    }

    public String getNombreCiudadano() {
        return nombreCiudadano;
    }

    public void setNombreCiudadano(String nombreCiudadano) {
        this.nombreCiudadano = nombreCiudadano;
    }
   
  
    
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPerfilFace() {
        return perfilFace;
    }

    public void setPerfilFace(String perfilFace) {
        this.perfilFace = perfilFace;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCiudadano == null) ? 0 : idCiudadano.hashCode());
		result = prime * result + ((nombreCiudadano == null) ? 0 : nombreCiudadano.hashCode());
		result = prime * result + ((perfilFace == null) ? 0 : perfilFace.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudadano other = (Ciudadano) obj;
		if (idCiudadano == null) {
			if (other.idCiudadano != null)
				return false;
		} else if (!idCiudadano.equals(other.idCiudadano))
			return false;
		if (nombreCiudadano == null) {
			if (other.nombreCiudadano != null)
				return false;
		} else if (!nombreCiudadano.equals(other.nombreCiudadano))
			return false;
		if (perfilFace == null) {
			if (other.perfilFace != null)
				return false;
		} else if (!perfilFace.equals(other.perfilFace))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

    

    
    
    
}
