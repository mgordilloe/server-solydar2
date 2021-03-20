package frt.utn.solydar.controlador;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import frt.utn.solydar.error.PerfilNotFoundException;

import frt.utn.solydar.modelo.Perfil;
import frt.utn.solydar.modelo.Usuario;
import frt.utn.solydar.repositorio.PerfilRepositorio;

@RestController
public class PerfilControlador {

	private final PerfilAssembler assembler;
	private final PerfilRepositorio repository;
	
	public PerfilControlador(PerfilAssembler assembler, PerfilRepositorio repositorio) {
		
		this.assembler = assembler;
		this.repository = repositorio;
	}
	
	@GetMapping("/perfiles/{id}")
	EntityModel<Perfil> one(@PathVariable Long id) {

	    Perfil perfil = repository.findById(id)
	    			.orElseThrow(() -> new PerfilNotFoundException(id));
		
	        return assembler.toModel(perfil);
	    }

	@GetMapping("/perfiles")
	CollectionModel<EntityModel<Perfil>> all() {

	  List<EntityModel<Perfil>> perfil = repository.findAll().stream() //
	      .map(assembler::toModel) //
	      .collect(Collectors.toList());

	  return CollectionModel.of(perfil, linkTo(methodOn(PerfilControlador.class).all()).withSelfRel());
	  
	 }

	@PostMapping("/perfil")
	ResponseEntity<?> newPerfil(@RequestBody Perfil newPerfil) {

	  EntityModel<Perfil> entityModel = assembler.toModel(repository.save(newPerfil));

	  return ResponseEntity //
	      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
	      .body(entityModel);
	}

	   
	@PutMapping("/perfil/{id}")
	ResponseEntity<?> replacePerfil(@RequestBody Perfil newPerfil, @PathVariable Long id) {

	  Perfil updatePerfil = repository.findById(id) //
	      .map(perfil -> {
	        perfil.setNombrePerfil(newPerfil.getNombrePerfil());
	        return repository.save(perfil);
	      }) //
	      .orElseGet(() -> {
	        newPerfil.setIdPerfil(id);
	        return repository.save(newPerfil);
	      });

	  EntityModel<Perfil> entityModel = assembler.toModel(updatePerfil);

	  return ResponseEntity //
	      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
	      .body(entityModel);
	}

	@DeleteMapping("/perfil/{id}")
	ResponseEntity<?> deletePerfil(@PathVariable Long id) {

	  repository.deleteById(id);

	  return ResponseEntity.noContent().build();
	}
	
	
}
