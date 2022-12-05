package com.sena.citas.controlador;

import com.sena.citas.entidad.usuario;
import com.sena.citas.servicio.usuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class usuarioControlador {
    @Autowired
    private usuarioServicioImpl servicio;

    @GetMapping()
    public List<usuario> obtenerusuarios(){
        return servicio.listarTodosLosUsuarios();
    }

    @PostMapping()
    public usuario nuevoUsuario(@RequestBody usuario user){
        return this.servicio.guardarUsuario(user);
    }
}
