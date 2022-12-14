package com.sena.citas.servicio;

import com.sena.citas.entidad.cita;

import java.util.List;

public interface citaServicio {
    List<cita> listarTodasLasCitas();
    cita guardarCita(cita cita);
    cita obtenerCitaPorId(int id);

    void eliminarCitaPorDetalleId(int id);

    //eliminar cita por id
    void eliminarCitaPorId(int id);
    // citas de los clientes
    List<cita> buscarCitasDelUsuarioPorId(int id);

}
