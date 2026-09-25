package com.bovinet.gestion.repository;

import com.bovinet.gestion.model.Bovino;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para la entidad Bovino.
 *
 * Al extender JpaRepository, Spring Data JPA genera automáticamente
 * la implementación de las operaciones CRUD básicas (save, findAll,
 * findById, deleteById, existsById), aplicando uno de los patrones
 * característicos del framework Spring Boot.
 */
public interface BovinoRepository extends JpaRepository<Bovino, String> {
}
