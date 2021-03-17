/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.utn.solydar.controlador;


import frt.utn.solydar.modelo.Ciudadano;
import frt.utn.solydar.modelo.Usuario;
import frt.utn.solydar.error.CiudadanoNotFoundException;
import frt.utn.solydar.error.UsuarioNotFoundException;
import frt.utn.solydar.repositorio.CiudadanoRepositorio;
import frt.utn.solydar.repositorio.UsuarioRepositorio;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Mariano
 */

@RestController
public class CiudadanoControlador {
    
    private final CiudadanoRepositorio repository;
    private final CiudadanoAssembler assembler;

    public CiudadanoControlador(CiudadanoRepositorio repository, CiudadanoAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
    

    
@GetMapping("/ciudadanos/{id}")
EntityModel<Ciudadano> one(@PathVariable Long id) {

        Ciudadano ciudadano = repository.findById(id) 
                .orElseThrow(() -> new CiudadanoNotFoundException(id));

        return assembler.toModel(ciudadano);
    }

@GetMapping("/ciudadanos")
CollectionModel<EntityModel<Ciudadano>> all() {

  List<EntityModel<Ciudadano>> ciudadano = repository.findAll().stream() //
      .map(assembler::toModel) //
      .collect(Collectors.toList());

  return CollectionModel.of(ciudadano, linkTo(methodOn(CiudadanoControlador.class).all()).withSelfRel());
  
 }

@PostMapping("/ciudadano")
ResponseEntity<?> newCiudadano(@RequestBody Ciudadano newCiudadano) {

  EntityModel<Ciudadano> entityModel = assembler.toModel(repository.save(newCiudadano));

  return ResponseEntity //
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
      .body(entityModel);
}

@PostMapping("/insertarUsuarioCiudadano")
ResponseEntity<?> newUsuarioCiudadano(@RequestBody Ciudadano newCiudadano,@RequestBody Usuario newUsuario){
    
    newCiudadano.setUsuario(newUsuario);
    
    EntityModel<Ciudadano> entityModel = assembler.toModel(repository.save(newCiudadano));
    
    return ResponseEntity //
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
      .body(entityModel);
}
    
@PutMapping("/ciudadano/{id}")
ResponseEntity<?> replaceUsuario(@RequestBody Ciudadano newCiudadano, @PathVariable Long id) {

  Ciudadano updateCiudadano = repository.findById(id) //
      .map(ciudadano -> {
        ciudadano.setNombreCiudadano(newCiudadano.getNombreCiudadano());
        ciudadano.setPerfilFace(newCiudadano.getPerfilFace());
        ciudadano.setTelefono(newCiudadano.getTelefono());
        return repository.save(ciudadano);
      }) //
      .orElseGet(() -> {
        newCiudadano.setIdCiudadano(id);
        return repository.save(newCiudadano);
      });

  EntityModel<Ciudadano> entityModel = assembler.toModel(updateCiudadano);

  return ResponseEntity //
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
      .body(entityModel);
}

@DeleteMapping("/ciudadano/{id}")
ResponseEntity<?> deleteCiudadano(@PathVariable Long id) {

  repository.deleteById(id);

  return ResponseEntity.noContent().build();
}
}






