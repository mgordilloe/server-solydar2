package frt.utn.solydar.error;

public class PerfilNotFoundException extends RuntimeException {
	public PerfilNotFoundException(Long id){
        super("No se pudo encontrar el perfil "+ id);
    }
}
