package com.sena.citas.controlador;

import com.sena.citas.entidad.detalleServicios;
import com.sena.citas.servicio.citaServicioImpl;
import com.sena.citas.servicio.detalleServicioServicioImpl;
import com.sena.citas.servicio.serviciosPrestadosServicioImpl;
import com.sena.citas.servicio.usuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Controller
public class detalleServiciosControlador {
    @Autowired
    private detalleServicioServicioImpl detalleServicio;
    @Autowired
    private usuarioServicioImpl usuarioServicio;
    @Autowired
    private serviciosPrestadosServicioImpl serviciosDisponibles;
    @Autowired
    private citaServicioImpl servicioCita;

    @GetMapping("/servicios/asignados")
    public String listarServiciosAsignados(Model modelo){
        modelo.addAttribute("serviciosAsignados", detalleServicio.listarTodosLosDetallesDeServicio());
        return "servicios/listar-asignados";
    }
    @PostMapping("/servicios/asignados")
    public String guardarDetalleServicio(@ModelAttribute("asignarServicio") detalleServicios detalleS){
        detalleServicio.guardarDetalleServicios(detalleS);
        return "redirect:/servicios/asignados";
    }
    @GetMapping("/servicios/asignar")
    public String formularioNuevoDetalleServicio(Model modelo) {
        detalleServicios detalleS = new detalleServicios();
        modelo.addAttribute("asignarServicio", detalleS);
        modelo.addAttribute("estilistas", usuarioServicio.listarEstilistas(2));
        modelo.addAttribute("servicios", serviciosDisponibles.listarTodosLosServiciosPrestados());

        //fechas para el formulario
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaActual = new Date();
        String stringFechaMin = formateador.format(fechaActual);
        //asignacion del año maximo
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaActual);
        calendario.add(Calendar.MONTH, 3);
        Date fechaMaxima = new Date();
        fechaMaxima = calendario.getTime();
        String stringFechaMaxima = formateador.format(fechaMaxima);
        //enviar las variables al front end
        modelo.addAttribute("fechaMinima", stringFechaMin);
        modelo.addAttribute("fechaMaxima", stringFechaMaxima);

        return "servicios/asignar-servicio";
    }
    //eliminar detalle de servicio
    @GetMapping("/servicios/asignados/{id}")
    public String eliminarDetalleServicio(@PathVariable int id){
        //eliminar cada cita vinculada a cada servicio
        servicioCita.eliminarCitaPorDetalleId(id);
        detalleServicio.eliminardetalleServicioPorId(id);
        return"redirect:/servicios/asignados";
    }
    // editar detalle de servicio
    @GetMapping("/servicios/editar_detalle/{id}")
    public String mostrarFormularioEditarDetalleServicio(@PathVariable int id, Model modelo){
        modelo.addAttribute("asignarServicio", detalleServicio.obtenerDetalleServicioPorId(id));
        modelo.addAttribute("estilistas", usuarioServicio.listarEstilistas(2));
        modelo.addAttribute("servicios", serviciosDisponibles.listarTodosLosServiciosPrestados());

        return "servicios/editar-detalle-servicio";
    }
    @PostMapping("/servicios/asignados/{id}")
    public String actualizarDetalleServicio(@PathVariable int id, @ModelAttribute("asignarServicio") detalleServicios asignarServicio){
        detalleServicios detalleServicioExistente = detalleServicio.obtenerDetalleServicioPorId(id);
        detalleServicioExistente.setId(id);
        detalleServicioExistente.setDisponibilidad(asignarServicio.isDisponibilidad());
        detalleServicioExistente.setFecha(asignarServicio.getFecha());
        detalleServicioExistente.setHora(asignarServicio.getHora());
        detalleServicioExistente.setUsuario(asignarServicio.getUsuario());
        detalleServicioExistente.setServicioPrestado(asignarServicio.getServicioPrestado());

        return "redirect:/servicios/asignados";
    }
}
