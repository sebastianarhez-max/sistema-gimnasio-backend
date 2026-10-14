# FORJA API – Gestión de Gimnasio (Spring Boot + PostgreSQL + JWT)

Arquitectura por capas: `entity` · `repository` · `service` (+ `impl`) · `controller` · `dto` · `security` · `util`.

## Variables de entorno
| Variable | Ejemplo |
|---|---|
| DB_URL | jdbc:postgresql://HOST:5432/BASE?sslmode=require |
| DB_USER | forja_user |
| DB_PASSWORD | (tu contraseña) |
| JWT_SECRET | (opcional, mínimo 64 caracteres) |

## Seguridad
1. `POST /url/auth/login` con `{"login":"carlos","password":"1234"}` devuelve el token.
2. Todos los demás endpoints requieren el header `Authorization: Bearer <token>`.

## CRUD (GET lista, GET /{id}, POST, PUT, DELETE /{id})
/url/usuario · entrenador · cliente · actividad · horario · clientehorario · plan · membresia · tipopago · pago ·
rutina · evaluacion · categoria · producto · venta · detalleventa · torneo · inscripciontorneo · asistencia

- PUT lleva el id dentro del JSON y reemplaza el objeto completo.
- DELETE hace baja lógica (estado = false) en tablas con `estado`.
- Relaciones: `"cliente": {"idCliente": 1}`.

## Registros con reglas de negocio
POST /url/registro/membresia · /venta · /asistencia/reservar · /asistencia/ingreso — PUT /url/registro/asistencia/{id}/falto

## Reportes (DTO)
/url/reporte/membresias-por-plan · asistencia-por-estado · ingresos-por-metodo-pago · clases-mas-concurridas · productos-mas-vendidos

## Consultas
/url/consulta/asistencia · recaudado-clase · recaudado-clase/por-pago · cupos · membresias-vigentes ·
membresias-por-vencer · cliente/{id}/historial-asistencia · productos-stock-bajo · ventas · ingresos ·
torneo/{id}/inscritos · entrenador/{id}/horarios
