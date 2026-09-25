package com.bovinet.gestion.service;

import com.bovinet.gestion.exception.AreteDuplicadoException;
import com.bovinet.gestion.exception.BovinoNoEncontradoException;
import com.bovinet.gestion.model.Bovino;
import com.bovinet.gestion.repository.BovinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de negocio del módulo Gestión de Bovinos.
 *
 * Concentra las reglas de negocio (validación de arete duplicado,
 * existencia del registro al editar/eliminar) para mantener los
 * controladores livianos, siguiendo el patrón de capas propio de
 * Spring Boot: Controller -> Service -> Repository.
 */
@Service
public class BovinoService {

    private final BovinoRepository bovinoRepository;

    /**
     * Inyección de dependencias por constructor (buena práctica
     * recomendada por el propio framework Spring frente a la
     * inyección por campo con @Autowired).
     */
    public BovinoService(BovinoRepository bovinoRepository) {
        this.bovinoRepository = bovinoRepository;
    }

    /**
     * Devuelve el listado completo de bovinos registrados,
     * ordenado por código de arete.
     */
    public List<Bovino> listarTodos() {
        return bovinoRepository.findAll();
    }

    /**
     * Busca un bovino por su código de arete.
     *
     * @throws BovinoNoEncontradoException si no existe un bovino con ese arete
     */
    public Bovino buscarPorArete(String arete) {
        return bovinoRepository.findById(arete)
                .orElseThrow(() -> new BovinoNoEncontradoException(arete));
    }

    /**
     * Registra un nuevo bovino, validando previamente que el código
     * de arete no exista ya en el sistema (RF-10).
     *
     * @throws AreteDuplicadoException si el arete ya está registrado
     */
    public Bovino registrar(Bovino bovino) {
        if (bovinoRepository.existsById(bovino.getArete())) {
            throw new AreteDuplicadoException(bovino.getArete());
        }
        return bovinoRepository.save(bovino);
    }

    /**
     * Actualiza los datos de un bovino existente.
     *
     * @throws BovinoNoEncontradoException si el bovino no existe
     */
    public Bovino actualizar(String arete, Bovino datosActualizados) {
        Bovino existente = buscarPorArete(arete);
        existente.setNombre(datosActualizados.getNombre());
        existente.setRaza(datosActualizados.getRaza());
        existente.setSexo(datosActualizados.getSexo());
        existente.setFechaNacimiento(datosActualizados.getFechaNacimiento());
        existente.setPesoActual(datosActualizados.getPesoActual());
        existente.setEstado(datosActualizados.getEstado());
        return bovinoRepository.save(existente);
    }

    /**
     * Da de baja (elimina) un bovino del sistema.
     *
     * @throws BovinoNoEncontradoException si el bovino no existe
     */
    public void eliminar(String arete) {
        Bovino existente = buscarPorArete(arete);
        bovinoRepository.delete(existente);
    }
}
