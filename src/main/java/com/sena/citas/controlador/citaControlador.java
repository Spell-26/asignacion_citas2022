package com.sena.citas.controlador;

import com.sena.citas.entidad.cargo;
import com.sena.citas.entidad.cita;
import com.sena.citas.entidad.detalleServicios;
import com.sena.citas.servicio.citaServicioImpl;
import com.sena.citas.servicio.detalleServicioServicioImpl;
import com.sena.citas.servicio.usuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class citaControlador {
    @Autowired
    private citaServicioImpl servicioCita;
    @Autowired
    private usuarioServicioImpl servicioUsuario;
    @Autowired
    private detalleServicioServicioImpl servicioDetalle;

    @GetMapping("/citas/listar")
    public String listarCitasAsignadas(Model modelo){
        modelo.addAttribute("citasAsignadas", servicioCita.listarTodasLasCitas());
        return "citas/listar";
    }
    @GetMapping("/citas/buscar")
    public String buscarCitaPorFecha(){
        return "citas/calendario";
    }
    @GetMapping ("/citas/nueva-cita")
    public String formularioNuevaCita(@Param("fecha") String fecha, Model modelo){
        cita cita = new cita();
        modelo.addAttribute("cita", cita);
        modelo.addAttribute("clientes", servicioUsuario.listarEstilistas(5));
        modelo.addAttribute("citasDisponibles", servicioDetalle.filtrarPorFecha(fecha));
        return "citas/crear-cita";

    }
    @PostMapping("citas/listar")
    public String guardarCita(@ModelAttribute("cita") cita citaAsignada){
        // se cambia la disponibilidad de la cita
        detalleServicios detalle = citaAsignada.getDetalleS();
        boolean estado = false;
        int id = detalle.getId();
        //modificacion de disponibilidad del servicio
        servicioDetalle.cambioDisponibilidad(estado,id);
        //se guarda la cita
        servicioCita.guardarCita(citaAsignada);
        return "redirect:/citas/listar";
    }
    //eliminar cita y volver a dejar disponible el detalle del servicio
    @GetMapping("citas/listar/{id}")
    public String eliminarCita(@PathVariable int id){
        //cambiar el estado de el detalle de servicio
        cita cita = servicioCita.obtenerCitaPorId(id);
        detalleServicios dServicio = cita.getDetalleS();
        int idDetalleServicios = dServicio.getId();
        servicioDetalle.cambioDisponibilidad(true, idDetalleServicios);
        //eliminar la cita
        servicioCita.eliminarCitaPorId(id);
        return "redirect:/citas/listar";
    }
    //editar cita
    @GetMapping("/citas/editar/{id}")
    public String mostrarFormularioEditarCita(@PathVariable int id, Model modelo){
        cita cita = servicioCita.obtenerCitaPorId(id);
        detalleServicios detalleCita = cita.getDetalleS();
        cargo cargo = cita.getCliente().getCargo();
        int nivel = cargo.getNivel();
        String fecha = detalleCita.getFecha();
        modelo.addAttribute("cita", cita);
        modelo.addAttribute("clientes", servicioUsuario.listarEstilistas(nivel));
        modelo.addAttribute("citasDisponibles", servicioDetalle.filtrarPorFecha(fecha));

        return "citas/editar_cita";
    }
    @PostMapping("citas/listar/{id}")
    public String actualizarCita(@PathVariable int id, @ModelAttribute("cita") cita cita){
        cita citaExistente = servicioCita.obtenerCitaPorId(id);
        citaExistente.setId(id);
        citaExistente.setDetalleS(cita.getDetalleS());
        citaExistente.setCliente(cita.getCliente());

        servicioCita.guardarCita(citaExistente);

        return "redirect:/citas/listar";
    }
}
