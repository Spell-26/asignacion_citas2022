package com.sena.citas.servicio;

import com.sena.citas.entidad.detalleServicios;

import java.util.List;

public interface detalleServicioServicio {
    public List<detalleServicios>listarTodosLosDetallesDeServicio();
    public detalleServicios guardarDetalleServicios(detalleServicios detalleServicios);


}
