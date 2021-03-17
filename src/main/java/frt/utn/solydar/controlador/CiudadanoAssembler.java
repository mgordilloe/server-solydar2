/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.utn.solydar.controlador;

import frt.utn.solydar.modelo.Ciudadano;

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
public class CiudadanoAssembler implements RepresentationModelAssembler<Ciudadano,EntityModel<Ciudadano>> {

    @Override
    public EntityModel<Ciudadano> toModel(Ciudadano ciudadano) {
        return EntityModel.of(ciudadano, //
            linkTo(methodOn(UsuarioControlador.class).one(ciudadano.getIdCiudadano())).withSelfRel(),
            linkTo(methodOn(UsuarioControlador.class).all()).withRel("ciudadanos"));
    }
    
}

