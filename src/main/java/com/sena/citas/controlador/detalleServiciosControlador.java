package com.sena.citas.controlador;

import com.sena.citas.entidad.detalleServicios;
import com.sena.citas.servicio.detalleServicioServicioImpl;
import com.sena.citas.servicio.serviciosPrestadosServicioImpl;
import com.sena.citas.servicio.usuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class detalleServiciosControlador {
    @Autowired
    private detalleServicioServicioImpl detalleServicio;
    @Autowired
    private usuarioServicioImpl usuarioServicio;
    @Autowired
    private serviciosPrestadosServicioImpl serviciosDisponibles;


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
    public String formularioNuevoDetalleServicio(Model modelo){
        detalleServicios detalleS = new detalleServicios();
        modelo.addAttribute("asignarServicio", detalleS);
        modelo.addAttribute("estilistas", usuarioServicio.listarEstilistas(2));
        modelo.addAttribute("servicios", serviciosDisponibles.listarTodosLosServiciosPrestados());
        return "servicios/asignar-servicio";
    }

}
