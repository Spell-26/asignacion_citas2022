package com.sena.citas.controlador;

import com.sena.citas.entidad.cargo;
import com.sena.citas.entidad.cita;
import com.sena.citas.entidad.detalleServicios;
import com.sena.citas.entidad.usuario;
import com.sena.citas.servicio.cargoServicioImpl;
import com.sena.citas.servicio.citaServicioImpl;
import com.sena.citas.servicio.detalleServicioServicioImpl;
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
    @Autowired
    private citaServicioImpl servicioCita;
    @Autowired
    private detalleServicioServicioImpl servicioDetalleS;

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
    @GetMapping("/gestion")
    public String gestionHub(){
        return "gestion";
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
    //eliminar usuarios
    @GetMapping("/usuarios/listar/{id}")
    public String eliminarUsuario(@PathVariable int id){
        //obtener las citas pedidas por parte del usuario
        List<cita> citasCliente = servicioCita.buscarCitasDelUsuarioPorId(id);
        //se eliminan cada una de las citas que pertenecen al usuario
        for(int i = 0; i < citasCliente.size(); i++){
            cita cita = citasCliente.get(i);
            int idCita = cita.getId();
            //volver a dejar el detalle de cita disponible
            detalleServicios dServicio = cita.getDetalleS();
            int idDetalleServicios = dServicio.getId();
            servicioDetalleS.cambioDisponibilidad(true, idDetalleServicios);
            //eliminar cita
            servicioCita.eliminarCitaPorId(idCita);
        }
        //obtener el usuario y su nivel de cargo
        usuario user = servicio.obtenerUsuarioPorId(id);
        cargo cargoUser = user.getCargo();
        int nivelCargo = cargoUser.getNivel();
        //en caso de que el usuario sea un estilista (nivel 2) se ejecutará esta parte del codigo
        if(nivelCargo == 2){
            List<detalleServicios> detalleServicios = servicioDetalleS.detallesVinculadosAEstilista(id);
            for(int j = 0; j < detalleServicios.size(); j++){
                detalleServicios storedService = detalleServicios.get(j);
                int idDetalleServicio = storedService.getId();
                //eliminar cada cita vinculada a cada servicio
                servicioCita.eliminarCitaPorDetalleId(idDetalleServicio);
                //eliminar detalle servicios
                servicioDetalleS.eliminardetalleServicioPorId(idDetalleServicio);
            }
        }
        servicio.eliminarUsuarioPorId(id);
        return "redirect:/usuarios/listar";
    }
    // editar usuarios
    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable int id, Model modelo){
        modelo.addAttribute("user", servicio.obtenerUsuarioPorId(id));
        modelo.addAttribute("cargos", servicioCargo.listarTodosLosCargos());
        return "usuarios/editar-usuario";
    }
    @PostMapping("/usuarios/listar/{id}")
    public String actualizarUsuario(@PathVariable int id, @ModelAttribute("user") usuario user){
        usuario usuarioExistente = servicio.obtenerUsuarioPorId(id);
        usuarioExistente.setId(id);
        usuarioExistente.setNombre(user.getNombre());
        usuarioExistente.setApellido(user.getApellido());
        usuarioExistente.setEmail(user.getEmail());
        usuarioExistente.setCargo(user.getCargo());

        servicio.guardarUsuario(usuarioExistente);

        return "redirect:/usuarios/listar";
    }
}
