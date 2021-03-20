package frt.utn.solydar.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PerfilNotFoundAdvice {
	@ResponseBody
    @ExceptionHandler(PerfilNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String PerfilNotFoundHandler(PerfilNotFoundException ex){
        return ex.getMessage();
    }
}
