package com.bovinet.gestion.controller;

import com.bovinet.gestion.exception.AreteDuplicadoException;
import com.bovinet.gestion.exception.BovinoNoEncontradoException;
import com.bovinet.gestion.model.Bovino;
import com.bovinet.gestion.service.BovinoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador web (Spring MVC) del módulo Gestión de Bovinos.
 *
 * Expone las rutas usadas por las historias de usuario HU-01 a HU-05
 * (ver HISTORIAS_DE_USUARIO.md) y delega toda la lógica de negocio en
 * BovinoService, devolviendo vistas Thymeleaf ubicadas en
 * src/main/resources/templates/bovinos/.
 */
@Controller
@RequestMapping("/bovinos")
public class BovinoController {

    private final BovinoService bovinoService;

    public BovinoController(BovinoService bovinoService) {
        this.bovinoService = bovinoService;
    }

    /**
     * HU-03: Listar todos los bovinos registrados.
     * GET /bovinos
     */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("bovinos", bovinoService.listarTodos());
        return "bovinos/list";
    }

    /**
     * Muestra el formulario vacío para registrar un nuevo bovino (HU-01).
     * GET /bovinos/nuevo
     */
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("bovino", new Bovino());
        model.addAttribute("esNuevo", true);
        return "bovinos/form";
    }

    /**
     * HU-01 y HU-02: Procesa el registro de un nuevo bovino, aplicando
     * las validaciones de Bean Validation definidas en la entidad
     * Bovino (arete obligatorio, longitudes, formato, peso positivo,
     * fecha no futura) y la regla de negocio de arete duplicado.
     * POST /bovinos/nuevo
     */
    @PostMapping("/nuevo")
    public String registrar(@Valid @ModelAttribute("bovino") Bovino bovino,
                             BindingResult resultado,
                             Model model) {
        if (resultado.hasErrors()) {
            model.addAttribute("esNuevo", true);
            return "bovinos/form";
        }
        try {
            bovinoService.registrar(bovino);
            return "redirect:/bovinos";
        } catch (AreteDuplicadoException ex) {
            model.addAttribute("errorArete", ex.getMessage());
            model.addAttribute("esNuevo", true);
            return "bovinos/error-arete";
        }
    }

    /**
     * HU-04: Muestra el formulario de edición prellenado con los
     * datos del bovino seleccionado.
     * GET /bovinos/{arete}/editar
     */
    @GetMapping("/{arete}/editar")
    public String formularioEditar(@PathVariable String arete, Model model) {
        try {
            model.addAttribute("bovino", bovinoService.buscarPorArete(arete));
            model.addAttribute("esNuevo", false);
            return "bovinos/form";
        } catch (BovinoNoEncontradoException ex) {
            model.addAttribute("mensaje", ex.getMessage());
            return "bovinos/no-encontrado";
        }
    }

    /**
     * HU-04: Procesa la actualización de un bovino existente.
     * POST /bovinos/{arete}/editar
     */
    @PostMapping("/{arete}/editar")
    public String actualizar(@PathVariable String arete,
                              @Valid @ModelAttribute("bovino") Bovino bovino,
                              BindingResult resultado,
                              Model model) {
        if (resultado.hasErrors()) {
            model.addAttribute("esNuevo", false);
            return "bovinos/form";
        }
        bovinoService.actualizar(arete, bovino);
        return "redirect:/bovinos";
    }

    /**
     * HU-05: Da de baja (elimina) un bovino del sistema.
     * POST /bovinos/{arete}/eliminar
     */
    @PostMapping("/{arete}/eliminar")
    public String eliminar(@PathVariable String arete, Model model) {
        try {
            bovinoService.eliminar(arete);
        } catch (BovinoNoEncontradoException ex) {
            model.addAttribute("mensaje", ex.getMessage());
        }
        return "redirect:/bovinos";
    }
}
