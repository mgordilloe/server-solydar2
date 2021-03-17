package frt.utn.solydar.repositorio;

import frt.utn.solydar.modelo.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author Mariano
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByEmailUsuario(String emailUsuario); //Esto es como hacer un select * from Usuario where email= ? 
}
