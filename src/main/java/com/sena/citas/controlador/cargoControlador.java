package com.sena.citas.controlador;


import com.sena.citas.entidad.cargo;
import com.sena.citas.servicio.cargoServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class cargoControlador {
    @Autowired
    private cargoServicioImpl servicio;

    //Ejecuto la funcion listar todos los cargos, esos cargos se almacenan el el atributo "cargosRegistrados"
    //y me hace un return al archivo html llamada cargos donde me mostrara con ayuda del thymeleaf la informacion
    //traida de la base de datos
    @GetMapping("/roles/listar")
    public String listarCargos(Model modelo){
        modelo.addAttribute("cargosRegistrados", servicio.listarTodosLosCargos());
        return "roles/cargos";
    }
    @GetMapping("/roles/nuevo")
    public String formularioNuevoCargo(Model modelo){
        cargo rol = new cargo();
        modelo.addAttribute("rolNuevo", rol);
        return "roles/nuevo-rol";
    }
    @PostMapping("/roles/listar")
    public String guardarRol(@ModelAttribute("rolNuevo") cargo cargo){
        servicio.guardarCargo(cargo);
        return "redirect:/roles/listar";
    }
}
