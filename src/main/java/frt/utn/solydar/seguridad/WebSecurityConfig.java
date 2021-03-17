/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.utn.solydar.seguridad;

/**
 *
 * @author Mariano
 */
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    
	//TUTORIAL SPRING SECURITY MITOCDE
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	protected void configure(AuthenticationManagerBuilder auth) 
	throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		http
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}
		
		//ESTO ES PARA HACER AUTENTICACION EN MEMORIA
//		auth
//			.inMemoryAuthentication()
//			.withUser("user")
//			.password("asdasd")
//			.roles("USER")
//			.and()
//			.withUser("admin")
//			.password("admin")
//			.roles("USER","ADMIN");
	
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//ESTO DE AQUI ES UN TUTORIAL QUE NO TERMINE
	
    //para hacer mi propio logue de usuario debo declarar la siguiente variable
//    @Autowired
//    UserDetailsService userDetailsService; 
//    
//	
//        @Autowired
//        public void configure(AuthenticationManagerBuilder auth) throws Exception{
//            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//            //Con esto le digo que quiero utilizar mi propio servicio de usuarios y la segunda parte dice que quiero utilizar 
//            //mi propio encriptador de claves
////            //Sino la manera de hacerlo con usuarios de Spring que son estaticos seria de la siguiente manera
////            //Habria que comentar la primera linea para usar esto
//
////            auth.inMemoryAuthentication()
////                    .withUser("user").password("123456").roles("USER").and()
////                    .withUser("manager").password("123456").roles("MANAGER").and()
////                    .withUser("admin").password("123456").roles("ADMIN","MANAGER","USER");
//        }
//        
//        @Bean
//        public PasswordEncoder passwordEncoder(){
//            return new BCryptPasswordEncoder();
//        }
//        
//        //Configuro la seguridad global del sistema
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//        
//            http.csrf().disable().httpBasic() //Token para que un formulario no pueda ser falsificado y habilito la autenticacion basica
//                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//No quiero que guarde las sesiones
//                    //.and().addFilter(JwtAhutorizationFilter()); //agreggo un filtro
//        
//        }
		
	
        //**********************************************
        //Esto utilziaba con la guia de spring
//	public void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.antMatchers("/", "/usuarios").permitAll()
//				.anyRequest().authenticated()
//				.and()
//			.formLogin()
//				.loginPage("/login")
//				.permitAll()
//				.and()
//			.logout()
//				.permitAll();
//	}

//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}

    
}
