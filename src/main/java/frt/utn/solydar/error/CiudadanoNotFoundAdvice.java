/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.utn.solydar.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Mariano
 */

@ControllerAdvice
public class CiudadanoNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(CiudadanoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String CiudadanoNotFoundHandler(CiudadanoNotFoundException ex){
        return ex.getMessage();
    }
}
