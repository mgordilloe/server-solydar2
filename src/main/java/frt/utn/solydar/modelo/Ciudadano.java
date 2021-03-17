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
    private int telefono;
    
    @Column(name="perfil_face_ciudadano")
    private String perfilFace;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    public Ciudadano() {
    }

    public Ciudadano(String nombreCiudadano, int telefono, String perfilFace) {
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
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
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.idCiudadano);
        hash = 67 * hash + Objects.hashCode(this.nombreCiudadano);
        hash = 67 * hash + this.telefono;
        hash = 67 * hash + Objects.hashCode(this.perfilFace);
        hash = 67 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ciudadano other = (Ciudadano) obj;
        if (this.telefono != other.telefono) {
            return false;
        }
        if (!Objects.equals(this.nombreCiudadano, other.nombreCiudadano)) {
            return false;
        }
        if (!Objects.equals(this.perfilFace, other.perfilFace)) {
            return false;
        }
        if (!Objects.equals(this.idCiudadano, other.idCiudadano)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }
    
    
    
}
