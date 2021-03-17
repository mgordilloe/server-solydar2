/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.utn.solydar.seguridad;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import frt.utn.solydar.modelo.Usuario;
import frt.utn.solydar.repositorio.UsuarioRepositorio;

/**
 *
 * @author Mariano
 */

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	//Inyecto el usuario con sus dependencias
	@Autowired
	private UsuarioRepositorio repo;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
    	Usuario user = repo.findByEmailUsuario(username);
    	
    	UserDetails userDet = userBuilder(user.getEmailUsuario(),user.getPasswordUsuario(),"ADMIN");
    	
    	return userDet;
           
    }

    private User userBuilder(String username, String password, String... roles) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(String rol:roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+rol));
        }
        return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
    
}
