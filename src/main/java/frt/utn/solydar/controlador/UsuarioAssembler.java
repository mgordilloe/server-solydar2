/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.utn.solydar.controlador;

import frt.utn.solydar.modelo.Usuario;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mariano
 */

@Component
public class UsuarioAssembler implements RepresentationModelAssembler<Usuario,EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario, //
            linkTo(methodOn(UsuarioControlador.class).one(usuario.getIdUsuario())).withSelfRel(),
            linkTo(methodOn(UsuarioControlador.class).all()).withRel("usuarios"));
    }
    
}
