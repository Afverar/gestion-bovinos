package com.bovinet.gestion.controller;

import com.bovinet.gestion.model.Bovino;
import com.bovinet.gestion.service.BovinoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API REST del módulo Gestión de Bovinos.
 *
 * Complementa el controlador MVC (BovinoController) exponiendo las
 * mismas operaciones en formato JSON, útil para una futura integración
 * con la aplicación móvil híbrida de la evidencia GA6-220501096-AA3-EV03
 * o con herramientas de prueba como Postman.
 */
@RestController
@RequestMapping("/api/bovinos")
public class BovinoRestController {

    private final BovinoService bovinoService;

    public BovinoRestController(BovinoService bovinoService) {
        this.bovinoService = bovinoService;
    }

    @GetMapping
    public List<Bovino> listar() {
        return bovinoService.listarTodos();
    }

    @GetMapping("/{arete}")
    public Bovino buscar(@PathVariable String arete) {
        return bovinoService.buscarPorArete(arete);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bovino registrar(@Valid @RequestBody Bovino bovino) {
        return bovinoService.registrar(bovino);
    }

    @PutMapping("/{arete}")
    public Bovino actualizar(@PathVariable String arete, @Valid @RequestBody Bovino bovino) {
        return bovinoService.actualizar(arete, bovino);
    }

    @DeleteMapping("/{arete}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String arete) {
        bovinoService.eliminar(arete);
    }

    /**
     * Manejador de excepciones local: traduce las excepciones de
     * negocio en respuestas HTTP con el código de estado adecuado,
     * en lugar de un error 500 genérico.
     */
    @ExceptionHandler(com.bovinet.gestion.exception.AreteDuplicadoException.class)
    public ResponseEntity<String> manejarAreteDuplicado(com.bovinet.gestion.exception.AreteDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(com.bovinet.gestion.exception.BovinoNoEncontradoException.class)
    public ResponseEntity<String> manejarNoEncontrado(com.bovinet.gestion.exception.BovinoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
