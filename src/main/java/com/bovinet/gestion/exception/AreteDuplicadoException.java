package com.bovinet.gestion.exception;

/**
 * Excepción de negocio lanzada cuando se intenta registrar un bovino
 * con un código de arete que ya existe en el sistema.
 *
 * Corresponde a la regla de negocio RF-10 documentada en la evidencia
 * GA5-220501095-AA1-EV03 ("El sistema debe impedir el registro de
 * códigos de arete duplicados") y a la pantalla de error prototipada
 * en GA5-220501095-AA1-EV04 / GA6-220501096-AA3-EV02.
 */
public class AreteDuplicadoException extends RuntimeException {

    public AreteDuplicadoException(String arete) {
        super("Ya existe un bovino registrado con el código de arete '" + arete + "'.");
    }
}
