package com.sena.citas.servicio;

import com.sena.citas.entidad.detalleServicios;
import com.sena.citas.repositorio.detalleServiciosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class detalleServicioServicioImpl implements detalleServicioServicio{

    @Autowired
    private detalleServiciosRepositorio repositorio;

    @Override
    public List<detalleServicios> listarTodosLosDetallesDeServicio() {
        return repositorio.findAll();
    }

    @Override
    public detalleServicios guardarDetalleServicios(detalleServicios detalleServicios) {
        return repositorio.save(detalleServicios);
    }

    @Override
    public List<detalleServicios> filtrarPorFecha(String fecha) {
        return repositorio.filtrarServicioPorFecha(fecha);
    }

    @Override
    public int cambioDisponibilidad(boolean estado, int id) {
      return  repositorio.cambioDisponibilidad(estado,id);
    }

    @Override
    public void eliminardetalleServicioPorId(int id) {
        repositorio.deleteById(id);
    }
    @Override
    public List<detalleServicios> detallesVinculadosAEstilista(int id) {
        return repositorio.detallesVinculadosAEstilista(id);
    }

    @Override
    public List<detalleServicios> detallesVinculadosAServicioPrestado(int id) {
        return repositorio.detallesVinculadosAServicioPrestado(id);
    }

    @Override
    public detalleServicios obtenerDetalleServicioPorId(int id) {
        return repositorio.findById(id).get();
    }


}
