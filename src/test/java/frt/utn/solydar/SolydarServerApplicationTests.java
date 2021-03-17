package frt.utn.solydar;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import frt.utn.solydar.modelo.Usuario;
import frt.utn.solydar.repositorio.UsuarioRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
class SolydarServerApplicationTests {

	@Autowired
	private UsuarioRepositorio repositorio;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Test
	void crearUsuarioTest() {
		Usuario usuario = new Usuario("gabyunt2",bCryptPasswordEncoder.encode("asdasd"));
		
		Usuario retorno = repositorio.save(usuario);
		
		assertTrue(retorno.getIdUsuario().equals(usuario.getIdUsuario()));
	}

}
