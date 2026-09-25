package com.bovinet.gestion.exception;

/**
 * Excepción de negocio lanzada cuando se busca, edita o elimina un
 * bovino cuyo código de arete no existe en el sistema.
 */
public class BovinoNoEncontradoException extends RuntimeException {

    public BovinoNoEncontradoException(String arete) {
        super("No existe ningún bovino registrado con el arete '" + arete + "'.");
    }
}
