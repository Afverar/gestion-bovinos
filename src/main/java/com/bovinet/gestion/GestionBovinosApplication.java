package com.bovinet.gestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del módulo "Gestión de Bovinos" de BovinNet.
 *
 * Este módulo corresponde a la evidencia GA7-220501096-AA3-EV01 y se
 * construye con Spring Boot, uno de los frameworks estudiados en el
 * componente formativo "Frameworks para construcción de aplicaciones
 * con JAVA".
 *
 * Al ejecutarse, levanta un servidor web embebido (Tomcat) en el
 * puerto 8080 y expone las vistas del módulo en la ruta /bovinos.
 *
 * @author Andrés Felipe Vera Rojas
 */
@SpringBootApplication
public class GestionBovinosApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionBovinosApplication.class, args);
    }
}
