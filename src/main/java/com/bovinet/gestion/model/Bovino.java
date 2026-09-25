package com.bovinet.gestion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entidad Bovino.
 *
 * Representa un bovino del hato ganadero, tal como se definió en el
 * modelo relacional de la evidencia GA6-220501096-AA1-EV01/EV02
 * (tabla BOVINO: arete, nombre, raza, sexo, fecha_nacimiento,
 * peso_actual, estado).
 *
 * Las anotaciones de validación (jakarta.validation) implementan las
 * reglas de negocio que se prueban en la evidencia GA7-220501096-AA3-EV02:
 * longitudes máximas, campos obligatorios, formato del código de arete
 * (sin caracteres especiales) y fechas coherentes.
 */
@Entity
@Table(name = "bovinos")
@Getter
@Setter
@NoArgsConstructor
public class Bovino {

    /**
     * Código de arete: llave primaria de negocio.
     * Solo admite letras y números (sin espacios ni caracteres
     * especiales), entre 4 y 10 caracteres, según la prueba de
     * validación de caracteres especiales de la evidencia EV02.
     */
    @Id
    @NotBlank(message = "El código de arete es obligatorio")
    @Size(min = 4, max = 10, message = "El arete debe tener entre 4 y 10 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "El arete solo puede contener letras y números, sin espacios ni símbolos")
    @Column(name = "arete", length = 10)
    private String arete;

    /**
     * Nombre del bovino (opcional según el modelo de datos original).
     */
    @Size(max = 60, message = "El nombre no puede superar los 60 caracteres")
    @Column(name = "nombre", length = 60)
    private String nombre;

    /**
     * Raza del bovino. Se maneja como texto libre en esta versión del
     * módulo; en una evolución futura se enlazará con la tabla RAZA
     * definida en el modelo relacional (normalización 3FN).
     */
    @NotBlank(message = "La raza es obligatoria")
    @Size(max = 40, message = "La raza no puede superar los 40 caracteres")
    @Column(name = "raza", length = 40)
    private String raza;

    /**
     * Sexo del bovino: HEMBRA o MACHO.
     */
    @NotNull(message = "Debe seleccionar el sexo del bovino")
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", length = 10)
    private Sexo sexo;

    /**
     * Fecha de nacimiento: no puede ser una fecha futura (prueba de
     * validación de fechas de la evidencia EV02).
     */
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @PastOrPresent(message = "La fecha de nacimiento no puede ser una fecha futura")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    /**
     * Peso actual en kilogramos: debe ser un número positivo (prueba
     * de validación de números de la evidencia EV02).
     */
    @NotNull(message = "El peso actual es obligatorio")
    @Positive(message = "El peso debe ser un número mayor que cero")
    @Column(name = "peso_actual")
    private Double pesoActual;

    /**
     * Estado del bovino dentro del hato.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 20)
    private Estado estado = Estado.ACTIVO;

    /**
     * Enumeración del sexo del bovino.
     */
    public enum Sexo { HEMBRA, MACHO }

    /**
     * Enumeración de los posibles estados de un bovino dentro del hato.
     */
    public enum Estado { ACTIVO, EN_OBSERVACION, BAJA }
}
