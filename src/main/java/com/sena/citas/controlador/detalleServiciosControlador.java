package com.sena.citas.controlador;

import com.sena.citas.entidad.detalleServicios;
import com.sena.citas.servicio.detalleServicioServicioImpl;
import com.sena.citas.servicio.serviciosPrestadosServicioImpl;
import com.sena.citas.servicio.usuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


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
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10 ));
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

}
