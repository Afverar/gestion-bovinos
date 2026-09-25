package com.bovinet.gestion.service;

import com.bovinet.gestion.exception.AreteDuplicadoException;
import com.bovinet.gestion.exception.BovinoNoEncontradoException;
import com.bovinet.gestion.model.Bovino;
import com.bovinet.gestion.repository.BovinoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias del servicio BovinoService.
 *
 * Estas pruebas complementan, a nivel de código (caja blanca), la
 * suite de pruebas de extremo a extremo construida con Playwright en
 * la evidencia GA9-220501096-AA1-EV01, verificando aquí puntualmente
 * la regla de negocio de arete duplicado (RF-10) sin necesidad de
 * levantar el servidor completo.
 */
@ExtendWith(MockitoExtension.class)
class BovinoServiceTest {

    @Mock
    private BovinoRepository bovinoRepository;

    @InjectMocks
    private BovinoService bovinoService;

    private Bovino bovinoDePrueba;

    @BeforeEach
    void configurar() {
        bovinoDePrueba = new Bovino();
        bovinoDePrueba.setArete("0412");
        bovinoDePrueba.setNombre("Estrella");
        bovinoDePrueba.setRaza("Cebu");
        bovinoDePrueba.setSexo(Bovino.Sexo.HEMBRA);
        bovinoDePrueba.setFechaNacimiento(LocalDate.of(2025, 6, 3));
        bovinoDePrueba.setPesoActual(245.0);
        bovinoDePrueba.setEstado(Bovino.Estado.ACTIVO);
    }

    @Test
    void registrarBovinoConAreteNuevoDebeGuardarseCorrectamente() {
        when(bovinoRepository.existsById("0412")).thenReturn(false);
        when(bovinoRepository.save(bovinoDePrueba)).thenReturn(bovinoDePrueba);

        Bovino resultado = bovinoService.registrar(bovinoDePrueba);

        assertEquals("0412", resultado.getArete());
        verify(bovinoRepository, times(1)).save(bovinoDePrueba);
    }

    @Test
    void registrarBovinoConAreteExistenteDebeLanzarExcepcion() {
        when(bovinoRepository.existsById("0412")).thenReturn(true);

        AreteDuplicadoException excepcion = assertThrows(
                AreteDuplicadoException.class,
                () -> bovinoService.registrar(bovinoDePrueba)
        );

        assertTrue(excepcion.getMessage().contains("0412"));
        verify(bovinoRepository, never()).save(any());
    }

    @Test
    void buscarBovinoInexistenteDebeLanzarExcepcion() {
        when(bovinoRepository.findById("9999")).thenReturn(Optional.empty());

        assertThrows(BovinoNoEncontradoException.class,
                () -> bovinoService.buscarPorArete("9999"));
    }

    @Test
    void eliminarBovinoExistenteDebeInvocarAlRepositorio() {
        when(bovinoRepository.findById("0412")).thenReturn(Optional.of(bovinoDePrueba));

        bovinoService.eliminar("0412");

        verify(bovinoRepository, times(1)).delete(bovinoDePrueba);
    }
}
