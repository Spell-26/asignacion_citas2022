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
}
