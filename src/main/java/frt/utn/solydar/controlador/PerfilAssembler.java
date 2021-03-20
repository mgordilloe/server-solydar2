package frt.utn.solydar.controlador;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import frt.utn.solydar.modelo.Perfil;

@Component
public class PerfilAssembler implements RepresentationModelAssembler<Perfil, EntityModel<Perfil>> {

	@Override
	public EntityModel<Perfil> toModel(Perfil perfil) {
		
		return EntityModel.of(perfil, //
	            linkTo(methodOn(PerfilControlador.class).one(perfil.getIdPerfil())).withSelfRel(),
	            linkTo(methodOn(PerfilControlador.class).all()).withRel("perfiles"));
	}

}
