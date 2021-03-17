/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.utn.solydar.modelo;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Mariano
 */
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long idUsuario;
    
    @Column(name="email_usuario")
    private String emailUsuario;
    
    @Column(name="password_usuario")
    private String passwordUsuario;
    
    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    private Ciudadano ciudadano;

    public Usuario() {
    }

    public Usuario(String emailUsuario, String passwordUsuario) {
        this.emailUsuario = emailUsuario;
        this.passwordUsuario = passwordUsuario;
    }

    public Usuario(Long idUsuario, String emailUsuario, String passwordUsuario) {
        this.idUsuario = idUsuario;
        this.emailUsuario = emailUsuario;
        this.passwordUsuario = passwordUsuario;
    }

    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.idUsuario);
        hash = 47 * hash + Objects.hashCode(this.emailUsuario);
        hash = 47 * hash + Objects.hashCode(this.passwordUsuario);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.emailUsuario, other.emailUsuario)) {
            return false;
        }
        if (!Objects.equals(this.passwordUsuario, other.passwordUsuario)) {
            return false;
        }
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        return true;
    }

   
    
    
    
}
