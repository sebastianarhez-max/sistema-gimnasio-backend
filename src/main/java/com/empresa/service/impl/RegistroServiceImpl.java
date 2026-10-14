package com.empresa.service.impl;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.empresa.dto.AsistenciaRegistroDTO;
import com.empresa.dto.ItemVentaDTO;
import com.empresa.dto.MembresiaRegistroDTO;
import com.empresa.dto.VentaRegistroDTO;
import com.empresa.entity.*;
import com.empresa.repository.*;
import com.empresa.security.UsuarioPrincipal;
import com.empresa.service.RegistroService;

@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired private ClienteRepository clienteRepo;
    @Autowired private PlanRepository planRepo;
    @Autowired private MembresiaRepository membresiaRepo;
    @Autowired private TipoPagoRepository tipoPagoRepo;
    @Autowired private PagoRepository pagoRepo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private ProductoRepository productoRepo;
    @Autowired private VentaRepository ventaRepo;
    @Autowired private DetalleVentaRepository detalleRepo;
    @Autowired private HorarioRepository horarioRepo;
    @Autowired private AsistenciaRepository asistenciaRepo;

    private static ResponseStatusException err(String msg) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
    }

    private static <T> T req(T value, String campo) {
        if (value == null) throw err("Falta el campo obligatorio: " + campo);
        return value;
    }

    /** Si no se envía idUsuario, se toma el administrador que está logueado (token JWT). */
    private Usuario usuarioActual(Integer idUsuario) {
        Integer id = idUsuario;
        if (id == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getPrincipal() instanceof UsuarioPrincipal) {
                id = ((UsuarioPrincipal) auth.getPrincipal()).getIdUsuario();
            }
        }
        return usuarioRepo.findById(req(id, "idUsuario")).orElseThrow(() -> err("El usuario no existe"));
    }

    // ------------------------------------------------------------ membresía + pago
    @Override
    @Transactional
    public Map<String, Object> registrarMembresia(MembresiaRegistroDTO dto) {
        Cliente c = clienteRepo.findById(req(dto.getIdCliente(), "idCliente")).orElseThrow(() -> err("El cliente no existe"));
        Plan p = planRepo.findById(req(dto.getIdPlan(), "idPlan")).orElseThrow(() -> err("El plan no existe"));
        TipoPago tp = tipoPagoRepo.findById(req(dto.getIdTipoPago(), "idTipoPago")).orElseThrow(() -> err("El tipo de pago no existe"));
        if (!Boolean.TRUE.equals(c.getEstado())) throw err("El cliente está inactivo");
        if (!Boolean.TRUE.equals(p.getEstado())) throw err("El plan está inactivo");

        LocalDate inicio = dto.getFechaInicio() != null ? dto.getFechaInicio() : LocalDate.now();
        Membresia m = new Membresia();
        m.setCliente(c);
        m.setPlan(p);
        m.setFechaInicio(inicio);
        m.setFechaFin(inicio.plusDays(p.getDuracionDias()));
        m = membresiaRepo.save(m);

        Pago pago = new Pago();
        pago.setMembresia(m);
        pago.setTipoPago(tp);
        pago.setMonto(p.getPrecio());
        pago.setConcepto("Pago membresía " + p.getNombre());
        pago = pagoRepo.save(pago);

        Map<String, Object> res = new LinkedHashMap<>();
        res.put("membresia", m);
        res.put("pago", pago);
        return res;
    }

    // ------------------------------------------------------------ venta + detalle + stock
    @Override
    @Transactional
    public Map<String, Object> registrarVenta(VentaRegistroDTO dto) {
        Usuario u = usuarioActual(dto.getIdUsuario());
        Cliente c = null;
        if (dto.getIdCliente() != null) {
            c = clienteRepo.findById(dto.getIdCliente()).orElseThrow(() -> err("El cliente no existe"));
        }
        if (dto.getItems() == null || dto.getItems().isEmpty()) throw err("La venta debe tener al menos un producto");

        Venta v = new Venta();
        v.setUsuario(u);
        v.setCliente(c);
        v.setTotal(0.0);
        v = ventaRepo.save(v);

        double total = 0;
        List<DetalleVenta> detalles = new ArrayList<>();
        for (ItemVentaDTO it : dto.getItems()) {
            Producto p = productoRepo.findById(req(it.getIdProducto(), "idProducto"))
                    .orElseThrow(() -> err("El producto " + it.getIdProducto() + " no existe"));
            if (it.getCantidad() == null || it.getCantidad() <= 0) throw err("Cantidad inválida para " + p.getNombre());
            if (!Boolean.TRUE.equals(p.getEstado())) throw err("El producto " + p.getNombre() + " está inactivo");
            if (p.getStock() < it.getCantidad()) {
                throw err("Stock insuficiente de " + p.getNombre() + " (disponible: " + p.getStock() + ")");
            }
            p.setStock(p.getStock() - it.getCantidad());
            productoRepo.save(p);

            DetalleVenta d = new DetalleVenta();
            d.setVenta(v);
            d.setProducto(p);
            d.setCantidad(it.getCantidad());
            d.setPrecioUnitario(p.getPrecio());
            d.setSubtotal(p.getPrecio() * it.getCantidad());
            total += d.getSubtotal();
            detalles.add(detalleRepo.save(d));
        }
        v.setTotal(Math.round(total * 100.0) / 100.0);
        v = ventaRepo.save(v);

        Map<String, Object> res = new LinkedHashMap<>();
        res.put("venta", v);
        res.put("detalles", detalles);
        return res;
    }

    // ------------------------------------------------------------ asistencia
    private static String sinTildes(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}", "").trim();
    }

    private static String nombreDia(LocalDate f) {
        switch (f.getDayOfWeek()) {
            case MONDAY: return "Lunes";
            case TUESDAY: return "Martes";
            case WEDNESDAY: return "Miercoles";
            case THURSDAY: return "Jueves";
            case FRIDAY: return "Viernes";
            case SATURDAY: return "Sabado";
            default: return "Domingo";
        }
    }

    private void validarClase(Horario h, Cliente c, LocalDate fecha, Asistencia existente) {
        if (!Boolean.TRUE.equals(h.getEstado())) throw err("El horario está inactivo");
        if (!Boolean.TRUE.equals(c.getEstado())) throw err("El cliente está inactivo");

        if (h.getFecha() != null) {
            if (!h.getFecha().equals(fecha)) throw err("Esta clase solo se dicta el " + h.getFecha());
        } else if (!sinTildes(h.getDia()).equalsIgnoreCase(nombreDia(fecha))) {
            throw err("Esta clase es de los días " + h.getDia() + " y la fecha indicada es " + nombreDia(fecha));
        }

        long ocupados = asistenciaRepo.countByHorario_IdHorarioAndFechaAndEstadoNot(
                h.getIdHorario(), fecha, EstadoAsistencia.FALTO);
        if (existente != null && existente.getEstado() != EstadoAsistencia.FALTO) ocupados--;
        if (h.getCupoMaximo() != null && ocupados >= h.getCupoMaximo()) throw err("La clase no tiene cupos disponibles");

        if (h.getTipoAcceso() == TipoAcceso.MEMBRESIA) {
            boolean vigente = membresiaRepo
                    .existsByCliente_IdClienteAndEstadoTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(
                            c.getIdCliente(), fecha, fecha);
            if (!vigente) throw err("El cliente no tiene una membresía vigente para esa fecha");
        }
    }

    @Override
    @Transactional
    public Asistencia reservar(AsistenciaRegistroDTO dto) {
        Horario h = horarioRepo.findById(req(dto.getIdHorario(), "idHorario")).orElseThrow(() -> err("El horario no existe"));
        Cliente c = clienteRepo.findById(req(dto.getIdCliente(), "idCliente")).orElseThrow(() -> err("El cliente no existe"));
        Usuario u = usuarioActual(dto.getIdUsuario());
        LocalDate fecha = dto.getFecha() != null ? dto.getFecha() : LocalDate.now();

        if (asistenciaRepo.findByHorario_IdHorarioAndCliente_IdClienteAndFecha(h.getIdHorario(), c.getIdCliente(), fecha).isPresent()) {
            throw err("El cliente ya tiene registro en esta clase para esa fecha");
        }
        validarClase(h, c, fecha, null);

        Asistencia a = new Asistencia();
        a.setHorario(h);
        a.setCliente(c);
        a.setUsuario(u);
        a.setFecha(fecha);
        a.setEstado(EstadoAsistencia.RESERVADO);
        a.setMontoPagado(BigDecimal.ZERO);
        return asistenciaRepo.save(a);
    }

    @Override
    @Transactional
    public Asistencia registrarIngreso(AsistenciaRegistroDTO dto) {
        Horario h = horarioRepo.findById(req(dto.getIdHorario(), "idHorario")).orElseThrow(() -> err("El horario no existe"));
        Cliente c = clienteRepo.findById(req(dto.getIdCliente(), "idCliente")).orElseThrow(() -> err("El cliente no existe"));
        Usuario u = usuarioActual(dto.getIdUsuario());
        LocalDate fecha = dto.getFecha() != null ? dto.getFecha() : LocalDate.now();

        Asistencia existente = asistenciaRepo
                .findByHorario_IdHorarioAndCliente_IdClienteAndFecha(h.getIdHorario(), c.getIdCliente(), fecha).orElse(null);
        if (existente != null && existente.getEstado() == EstadoAsistencia.ASISTIO) {
            throw err("El cliente ya registró su ingreso a esta clase");
        }
        validarClase(h, c, fecha, existente);

        Asistencia a = existente != null ? existente : new Asistencia();
        a.setHorario(h);
        a.setCliente(c);
        a.setUsuario(u);
        a.setFecha(fecha);
        a.setHoraIngreso(LocalDateTime.now());
        a.setEstado(EstadoAsistencia.ASISTIO);

        if (h.getTipoAcceso() == TipoAcceso.LIBRE) {
            TipoPago tp = tipoPagoRepo.findById(req(dto.getIdTipoPago(), "idTipoPago (obligatorio en clases LIBRE)"))
                    .orElseThrow(() -> err("El tipo de pago no existe"));
            a.setTipoPago(tp);
            a.setMontoPagado(h.getPrecioEntrada());
        } else {
            a.setTipoPago(null);
            a.setMontoPagado(BigDecimal.ZERO);
        }
        return asistenciaRepo.save(a);
    }

    @Override
    @Transactional
    public Asistencia marcarFalto(Integer idAsistencia) {
        Asistencia a = asistenciaRepo.findById(idAsistencia)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asistencia no encontrada"));
        if (a.getEstado() == EstadoAsistencia.ASISTIO) throw err("No se puede marcar falta: el cliente ya ingresó");
        a.setEstado(EstadoAsistencia.FALTO);
        return asistenciaRepo.save(a);
    }
}
