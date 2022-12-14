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

import java.util.List;

@Controller
public class cargoControlador {
    @Autowired
    private cargoServicioImpl servicio;
    @Autowired
    private usuarioServicioImpl servicioUsuario;
    @Autowired
    private detalleServicioServicioImpl servicioDetalleS;
    @Autowired
    private citaServicioImpl servicioCita;

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
    @GetMapping("/roles/cargos/{id}")
    public String eliminarRol(@PathVariable int id){
        cargo storedCargo = servicio.obtenerCargoPorId(id);
        int nivel = storedCargo.getNivel();
        List<usuario> user = servicioUsuario.listarEstilistas(nivel);
        for (int i = 0; i < user.size(); i++){
            usuario storedUser = user.get(i);
            //en caso de que el cargo eliminado sea el de estilistar se eliminaran todos los servicios asignados a ese estilista
            if (nivel == 2){
                //agarrar el id del usuario de nivel 2
                int idUser = storedUser.getId();
                //Buscar las citas asignadas al estilista
                List<detalleServicios> detalleServicios = servicioDetalleS.detallesVinculadosAEstilista(idUser);
                for(int j = 0; j < detalleServicios.size(); j++){
                    detalleServicios storedService = detalleServicios.get(j);
                    int idDetalleServicio = storedService.getId();
                    //eliminar cada cita vinculada a cada servicio
                    servicioCita.eliminarCitaPorDetalleId(idDetalleServicio);
                    //eliminar detalle servicios
                    servicioDetalleS.eliminardetalleServicioPorId(idDetalleServicio);
                }
            }
            servicioUsuario.eliminarPorCargo(id);
        }
        servicio.eliminarCargo(id);
        return "redirect:/roles/listar";
    }

    @GetMapping("/roles/editar/{id}")
    public String mostrarFormularioEditarCargo(@PathVariable int id, Model modelo){
        modelo.addAttribute("cargo", servicio.obtenerCargoPorId(id));
        return "roles/editar_cargo";
    }
    @PostMapping("/roles/listar/{id}")
    public String actualizarCargo(@PathVariable int id, @ModelAttribute("cargo") cargo cargo){
        cargo cargoExistente =servicio.obtenerCargoPorId(id);
        cargoExistente.setId(id);
        cargoExistente.setDescripcion(cargo.getDescripcion());
        cargoExistente.setNivel(cargo.getNivel());

        servicio.guardarCargo(cargoExistente);

        return "redirect:/roles/listar";
    }
}
