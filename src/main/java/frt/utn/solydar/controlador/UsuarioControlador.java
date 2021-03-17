/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.utn.solydar.controlador;


import frt.utn.solydar.modelo.Usuario;

import frt.utn.solydar.error.UsuarioNotFoundException;
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
public class UsuarioControlador {
    
    private final UsuarioRepositorio repository;
    private final UsuarioAssembler assembler;

    public UsuarioControlador(UsuarioRepositorio repository, UsuarioAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
    
@GetMapping("/hello")
public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
return String.format("Hello %s!", name);
}
    
@GetMapping("/usuarios/{id}")
EntityModel<Usuario> one(@PathVariable Long id) {

        Usuario usuario = repository.findById(id) 
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        return assembler.toModel(usuario);
    }

@GetMapping("/usuarios")
CollectionModel<EntityModel<Usuario>> all() {

  List<EntityModel<Usuario>> usuarios = repository.findAll().stream() //
      .map(assembler::toModel) //
      .collect(Collectors.toList());

  return CollectionModel.of(usuarios, linkTo(methodOn(UsuarioControlador.class).all()).withSelfRel());
  
 }

@PostMapping("/usuario")
ResponseEntity<?> newUsuario(@RequestBody Usuario newUsuario) {

  EntityModel<Usuario> entityModel = assembler.toModel(repository.save(newUsuario));

  return ResponseEntity //
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
      .body(entityModel);
}
    
@PutMapping("/usuario/{id}")
ResponseEntity<?> replaceUsuario(@RequestBody Usuario newUsuario, @PathVariable Long id) {

  Usuario updateUsuario = repository.findById(id) //
      .map(usuario -> {
        usuario.setEmailUsuario(newUsuario.getEmailUsuario());
        usuario.setPasswordUsuario(newUsuario.getPasswordUsuario());
        return repository.save(usuario);
      }) //
      .orElseGet(() -> {
        newUsuario.setIdUsuario(id);
        return repository.save(newUsuario);
      });

  EntityModel<Usuario> entityModel = assembler.toModel(updateUsuario);

  return ResponseEntity //
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
      .body(entityModel);
}

@DeleteMapping("/usuarios/{id}")
ResponseEntity<?> deleteUsuario(@PathVariable Long id) {

  repository.deleteById(id);

  return ResponseEntity.noContent().build();
}
}






