package com.empresa.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/** Consultas (reportes) en SQL nativo de PostgreSQL. */
@Repository
public class ConsultaRepository {

    @Autowired
    private JdbcTemplate jdbc;

    /** snake_case (columnas de PostgreSQL) -> camelCase (JSON). */
    private static String camel(String s) {
        StringBuilder b = new StringBuilder();
        boolean up = false;
        for (char ch : s.toCharArray()) {
            if (ch == '_') {
                up = true;
            } else {
                b.append(up ? Character.toUpperCase(ch) : ch);
                up = false;
            }
        }
        return b.toString();
    }

    private List<Map<String, Object>> q(String sql, Object... args) {
        List<Map<String, Object>> out = new ArrayList<>();
        for (Map<String, Object> row : jdbc.queryForList(sql, args)) {
            Map<String, Object> m = new LinkedHashMap<>();
            row.forEach((k, v) -> m.put(camel(k), v));
            out.add(m);
        }
        return out;
    }

    private static double num(Object o) {
        return o == null ? 0 : ((Number) o).doubleValue();
    }

    /** C1. Quién va a llegar / quién ya llegó a una clase en una fecha. */
    public List<Map<String, Object>> asistencia(Integer idHorario, LocalDate fecha) {
        return q("SELECT a.id_asistencia, c.id_cliente, c.nombres, c.apellidos, a.estado, a.hora_ingreso, a.monto_pagado FROM asistencia a JOIN cliente c ON c.id_cliente = a.id_cliente WHERE a.id_horario = ? AND a.fecha = ? ORDER BY a.hora_ingreso", idHorario, fecha);
    }

    /** C2. Recaudado hasta el momento en una clase y cuántas personas ingresaron. */
    public List<Map<String, Object>> recaudadoClase(Integer idHorario, LocalDate fecha) {
        return q("SELECT act.nombre AS clase, h.hora_inicio, h.hora_fin, h.precio_entrada, COUNT(a.id_asistencia) AS personas_ingresaron, COALESCE(SUM(a.monto_pagado),0) AS total_recaudado FROM horario h JOIN actividad act ON act.id_actividad = h.id_actividad LEFT JOIN asistencia a ON a.id_horario = h.id_horario AND a.fecha = ? AND a.estado = 'ASISTIO' WHERE h.id_horario = ? GROUP BY act.nombre, h.hora_inicio, h.hora_fin, h.precio_entrada", fecha, idHorario);
    }

    /** C3. Recaudado de una clase desglosado por método de pago. */
    public List<Map<String, Object>> recaudadoPorPago(Integer idHorario, LocalDate fecha) {
        return q("SELECT tp.nombre AS tipo_pago, COUNT(*) AS cantidad, SUM(a.monto_pagado) AS total FROM asistencia a JOIN tipo_pago tp ON tp.id_tipo_pago = a.id_tipo_pago WHERE a.id_horario = ? AND a.fecha = ? AND a.estado = 'ASISTIO' GROUP BY tp.nombre", idHorario, fecha);
    }

    /** C4. Cupos disponibles de una clase en una fecha. */
    public List<Map<String, Object>> cupos(Integer idHorario, LocalDate fecha) {
        return q("SELECT h.cupo_maximo, COUNT(a.id_asistencia) AS ocupados, h.cupo_maximo - COUNT(a.id_asistencia) AS disponibles FROM horario h LEFT JOIN asistencia a ON a.id_horario = h.id_horario AND a.fecha = ? AND a.estado <> 'FALTO' WHERE h.id_horario = ? GROUP BY h.id_horario, h.cupo_maximo", fecha, idHorario);
    }

    /** C5. Membresías vigentes hoy. */
    public List<Map<String, Object>> membresiasVigentes() {
        return q("SELECT m.id_membresia, c.id_cliente, c.nombres, c.apellidos, p.nombre AS plan, m.fecha_inicio, m.fecha_fin, (m.fecha_fin - CURRENT_DATE) AS dias_restantes FROM membresia m JOIN cliente c ON c.id_cliente = m.id_cliente JOIN plan p ON p.id_plan = m.id_plan WHERE m.estado = TRUE AND CURRENT_DATE BETWEEN m.fecha_inicio AND m.fecha_fin ORDER BY m.fecha_fin");
    }

    /** C6. Membresías que vencen en los próximos N días. */
    public List<Map<String, Object>> membresiasPorVencer(Integer dias) {
        return q("SELECT m.id_membresia, c.id_cliente, c.nombres, c.apellidos, c.telefono, p.nombre AS plan, m.fecha_fin, (m.fecha_fin - CURRENT_DATE) AS dias_restantes FROM membresia m JOIN cliente c ON c.id_cliente = m.id_cliente JOIN plan p ON p.id_plan = m.id_plan WHERE m.estado = TRUE AND m.fecha_fin BETWEEN CURRENT_DATE AND (CURRENT_DATE + CAST(? AS INTEGER)) ORDER BY m.fecha_fin", dias);
    }

    /** C7. Historial de asistencia de un cliente. */
    public List<Map<String, Object>> historialAsistencia(Integer idCliente) {
        return q("SELECT a.fecha, act.nombre AS actividad, CONCAT(e.nombres, ' ', e.apellidos) AS entrenador, h.hora_inicio, h.hora_fin, a.estado, a.monto_pagado FROM asistencia a JOIN horario h ON h.id_horario = a.id_horario JOIN actividad act ON act.id_actividad = h.id_actividad JOIN entrenador e ON e.id_entrenador = h.id_entrenador WHERE a.id_cliente = ? ORDER BY a.fecha DESC", idCliente);
    }

    /** C8. Productos con stock bajo (stock <= stock mínimo). */
    public List<Map<String, Object>> productosStockBajo() {
        return q("SELECT p.id_producto, p.nombre, cp.nombre AS categoria, p.stock, p.stock_minimo FROM producto p JOIN categoria_producto cp ON cp.id_categoria = p.id_categoria WHERE p.estado = TRUE AND p.stock <= p.stock_minimo ORDER BY p.stock");
    }

    /** C9. Ventas de la tienda en un rango de fechas. */
    public List<Map<String, Object>> ventasPorFecha(LocalDate desde, LocalDate hasta) {
        return q("SELECT v.id_venta, v.fecha, CONCAT(u.nombres, ' ', u.apellidos) AS vendedor, COALESCE(CONCAT(c.nombres, ' ', c.apellidos), 'Público general') AS cliente, v.total FROM venta v JOIN usuario u ON u.id_usuario = v.id_usuario LEFT JOIN cliente c ON c.id_cliente = v.id_cliente WHERE CAST(v.fecha AS DATE) BETWEEN ? AND ? ORDER BY v.fecha", desde, hasta);
    }

    /** C11. Inscritos de un torneo con su entrenador y resultado. */
    public List<Map<String, Object>> inscritosTorneo(Integer idTorneo) {
        return q("SELECT it.id_inscripcion, c.nombres, c.apellidos, it.categoria, it.sexo, CONCAT(e.nombres, ' ', e.apellidos) AS entrenador, it.puesto, it.puntaje FROM inscripcion_torneo it JOIN cliente c ON c.id_cliente = it.id_cliente LEFT JOIN entrenador e ON e.id_entrenador = it.id_entrenador WHERE it.id_torneo = ? ORDER BY it.puesto IS NULL, it.puesto", idTorneo);
    }

    /** C12. Horarios de un entrenador. */
    public List<Map<String, Object>> horariosEntrenador(Integer idEntrenador) {
        return q("SELECT h.id_horario, act.nombre AS actividad, h.dia, h.fecha, h.hora_inicio, h.hora_fin, h.cupo_maximo, h.tipo_acceso, h.precio_entrada FROM horario h JOIN actividad act ON act.id_actividad = h.id_actividad WHERE h.id_entrenador = ? AND h.estado = TRUE ORDER BY h.dia, h.hora_inicio", idEntrenador);
    }

    /** C10. Ingresos totales: membresías + tienda + clases libres. */
    public Map<String, Object> ingresos(LocalDate desde, LocalDate hasta) {
        Map<String, Object> m = new LinkedHashMap<>(q(
                "SELECT (SELECT COALESCE(SUM(monto),0) FROM pago WHERE CAST(fecha AS DATE) BETWEEN ? AND ?) AS membresias, "
                + "(SELECT COALESCE(SUM(total),0) FROM venta WHERE CAST(fecha AS DATE) BETWEEN ? AND ?) AS ventas_tienda, "
                + "(SELECT COALESCE(SUM(monto_pagado),0) FROM asistencia WHERE fecha BETWEEN ? AND ? AND estado = 'ASISTIO') AS clases_libres",
                desde, hasta, desde, hasta, desde, hasta).get(0));
        double total = num(m.get("membresias")) + num(m.get("ventasTienda")) + num(m.get("clasesLibres"));
        m.put("total", Math.round(total * 100.0) / 100.0);
        return m;
    }
}
