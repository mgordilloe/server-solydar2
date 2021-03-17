package frt.utn.solydar.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import frt.utn.solydar.modelo.Ciudadano;


/**
 *
 * @author Mariano
 */
public interface CiudadanoRepositorio extends JpaRepository<Ciudadano, Long> {
    
}
