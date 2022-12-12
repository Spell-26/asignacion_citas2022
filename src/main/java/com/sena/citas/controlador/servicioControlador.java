package com.sena.citas.controlador;

import com.sena.citas.entidad.serviciosPrestados;
import com.sena.citas.servicio.serviciosPrestadosServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class servicioControlador {
   @Autowired
   private serviciosPrestadosServicioImpl servicio;

    @GetMapping("/servicios/listar")
    public String listarServicios(Model modelo){
        modelo.addAttribute("serviciosPrestados", servicio.listarTodosLosServiciosPrestados());
        return "servicios/listar";
    }

    @GetMapping("/servicios/nuevo")
    public String formularioNuevoServicio(Model modelo){
        serviciosPrestados servPrestado = new serviciosPrestados();
        modelo.addAttribute("servicioNuevo", servPrestado);
        return "servicios/crear_servicio";
    }
    @PostMapping("/servicios/listar")
    public String guardarServicio(@ModelAttribute("servicioNuevo") serviciosPrestados servPrestado){
        servicio.guardarDServiciosPrestados(servPrestado);
        return "redirect:/servicios/listar";
    }
}
