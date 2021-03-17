/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.utn.solydar.error;

/**
 *
 * @author Mariano
 */
public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long id){
        super("No se pudo encontrar el usuario "+ id);
    }
}
