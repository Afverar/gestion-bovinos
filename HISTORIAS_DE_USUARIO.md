# Historias de usuario — Módulo Gestión de Bovinos

Artefacto de referencia para la codificación realizada en la evidencia
**GA7-220501096-AA3-EV01**, tomado como base para las pruebas de la
evidencia **GA7-220501096-AA3-EV02**. Se apoya en los requisitos
funcionales ya definidos en `GA5-220501095-AA1-EV03` (IEEE 830) y en el
modelo relacional de `GA6-220501096-AA1-EV01/EV02`.

---

## HU-01 — Registrar un nuevo bovino
**Como** administrador de la finca,
**quiero** registrar un nuevo bovino indicando su código de arete, nombre,
raza, sexo, fecha de nacimiento y peso,
**para** llevar el control individual de cada animal del hato.

**Criterios de aceptación**
- El código de arete, la raza, el sexo, la fecha de nacimiento y el peso son obligatorios.
- El código de arete admite únicamente letras y números, entre 4 y 10 caracteres.
- El peso debe ser un número mayor que cero.
- La fecha de nacimiento no puede ser una fecha futura.
- Requisito relacionado: RF-09.

## HU-02 — Impedir el registro de un arete duplicado
**Como** administrador de la finca,
**quiero** que el sistema rechace el registro de un bovino si el código de
arete ya existe,
**para** evitar inconsistencias en la información del hato.

**Criterios de aceptación**
- Al intentar guardar un arete ya registrado, se muestra una pantalla de
  error específica con el mensaje correspondiente.
- La pantalla de error ofrece una acción para volver al formulario o
  consultar el listado.
- Requisito relacionado: RF-10.

## HU-03 — Consultar el listado de bovinos
**Como** administrador de la finca,
**quiero** ver un listado con todos los bovinos registrados y su estado,
**para** conocer rápidamente la situación general del hato.

**Criterios de aceptación**
- El listado muestra arete, nombre, raza, sexo, fecha de nacimiento, peso y estado.
- El estado se distingue visualmente por color y por texto (Activo, En
  observación, Baja).
- Requisito relacionado: RF-12.

## HU-04 — Editar los datos de un bovino existente
**Como** administrador de la finca,
**quiero** editar los datos de un bovino ya registrado,
**para** mantener actualizada su información (peso, estado, etc.).

**Criterios de aceptación**
- El formulario de edición aparece prellenado con los datos actuales del bovino.
- El código de arete no puede modificarse una vez creado el registro.
- Se aplican las mismas validaciones que en el registro (HU-01).
- Requisito relacionado: RF-11.

## HU-05 — Dar de baja un bovino
**Como** administrador de la finca,
**quiero** eliminar/dar de baja un bovino del sistema,
**para** reflejar su salida del hato (venta, muerte, traslado).

**Criterios de aceptación**
- Se solicita confirmación antes de eliminar el registro.
- Si el bovino no existe, se muestra un mensaje claro en lugar de un error técnico.
