package com.sena.citas.controlador;

import com.sena.citas.entidad.cargo;
import com.sena.citas.entidad.usuario;
import com.sena.citas.servicio.cargoServicioImpl;
import com.sena.citas.servicio.usuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller

public class usuarioControlador {
    @Autowired
    private usuarioServicioImpl servicio;
    @Autowired
    private cargoServicioImpl servicioCargo;

    //Ejecuto la funcion listar todos los usuarios, esos usuarios se almacenan el el atributo "usuariosRegistrados"
    //y me hace un return al archivo html llamada usuarios donde me mostrara con ayuda del thymeleaf la informacion
    //traida de la base de datos
    @GetMapping("/usuarios/listar")
    public String listarUsuarios(Model modelo){
        modelo.addAttribute("usuariosRegistrados", servicio.listarTodosLosUsuarios());
        return "usuarios/listar";
    }
    @GetMapping("/")
    public String volverAlIndex(){
        return "index";
    }
    @GetMapping("/usuarios/nuevo")
    public String formularioRegistroUsuario(Model modelo){
        usuario usuario = new usuario();
        modelo.addAttribute("nuevoUser", usuario);
        modelo.addAttribute("cargos", servicioCargo.listarTodosLosCargos());
        return "usuarios/nuevo-usuario";
    }

    @PostMapping("/usuarios/listar")
    public String nuevoUsuario(@ModelAttribute("nuevoUser") usuario usuario){
        servicio.guardarUsuario(usuario);
        return "redirect:/usuarios/listar";
    }
    @GetMapping("/usuarios/estilistas")
    public String verEstilistas(Model modelo){
        modelo.addAttribute("estilistas", servicio.listarEstilistas(2));
        return "usuarios/listar-estilistas";
    }
}
