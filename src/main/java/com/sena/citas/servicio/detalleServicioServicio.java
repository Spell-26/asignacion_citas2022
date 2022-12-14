package com.sena.citas.servicio;

import com.sena.citas.entidad.detalleServicios;

import java.util.List;

public interface detalleServicioServicio {
    public List<detalleServicios>listarTodosLosDetallesDeServicio();
    public detalleServicios guardarDetalleServicios(detalleServicios detalleServicios);

    public List<detalleServicios> filtrarPorFecha(String fecha);

    public int cambioDisponibilidad(boolean estado, int id);

    void eliminardetalleServicioPorId(int id);

    public List<detalleServicios> detallesVinculadosAEstilista(int id);

    public List<detalleServicios> detallesVinculadosAServicioPrestado(int id);

    public detalleServicios obtenerDetalleServicioPorId(int id);
}
