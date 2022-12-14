package com.sena.citas.controlador;

import com.sena.citas.entidad.detalleServicios;
import com.sena.citas.entidad.serviciosPrestados;
import com.sena.citas.servicio.citaServicioImpl;
import com.sena.citas.servicio.detalleServicioServicioImpl;
import com.sena.citas.servicio.serviciosPrestadosServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class servicioControlador {
   @Autowired
   private serviciosPrestadosServicioImpl servicio;
   @Autowired
   private detalleServicioServicioImpl detalleServicio;
    @Autowired
    private citaServicioImpl servicioCita;

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
    //mapeo de eliminar servicio
    @GetMapping("/servicios/listar/{id}")
    public String eliminarServicio(@PathVariable int id){
        //obtener los detalles de servicios vinculados al servicio prestado
        List<detalleServicios> dServicios = detalleServicio.detallesVinculadosAServicioPrestado(id);
        for(int i = 0; i < dServicios.size(); i++){
            detalleServicios storedService = dServicios.get(i);
            int idDetalleServicio = storedService.getId();
            //eliminar detalle servicios
            detalleServicio.eliminardetalleServicioPorId(idDetalleServicio);
            //eliminar cada cita vinculada a cada servicio
            servicioCita.eliminarCitaPorDetalleId(idDetalleServicio);
        }
        servicio.eliminarServicioPrestadoPorId(id);
        return "redirect:/servicios/listar";
    }
    //formulario editar servicio prestado
    @GetMapping("/servicios/editar/{id}")
    public String mostrarFormularioEditarServicioPrestado(@PathVariable int id, Model modelo){
        modelo.addAttribute("servicioP", servicio.obtenerServicioPorId(id));
        return "servicios/editar-servicio-prestado";
    }
    @PostMapping("/servicios/listar/{id}")
    public String editarServicioPrestado(@PathVariable int id, @ModelAttribute("servicioP") serviciosPrestados servicioP){
        serviciosPrestados servicioPrestadoExistente = servicio.obtenerServicioPorId(id);
        servicioPrestadoExistente.setId(id);
        servicioPrestadoExistente.setNombreServicio(servicioP.getNombreServicio());
        servicioPrestadoExistente.setDescripcion(servicioP.getDescripcion());
        servicioPrestadoExistente.setDuracion(servicioP.getDuracion());

        servicio.guardarDServiciosPrestados(servicioPrestadoExistente);

        return "redirect:/servicios/listar";
    }
}
