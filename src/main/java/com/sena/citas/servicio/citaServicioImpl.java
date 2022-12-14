package com.sena.citas.servicio;

import com.sena.citas.entidad.cita;
import com.sena.citas.repositorio.citaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class citaServicioImpl implements citaServicio{
    @Autowired
    private citaRepositorio repositorio;

    @Override
    public List<cita> listarTodasLasCitas() {
        return repositorio.findAll();
    }

    @Override
    public cita guardarCita(cita cita) {
        return repositorio.save(cita);
    }

    @Override
    public cita obtenerCitaPorId(int id) {
        return repositorio.findById(id).get();
    }

    @Override
    public void eliminarCitaPorDetalleId(int id) {
        repositorio.eliminarCitaPorDetalleId(id);
    }

    @Override
    public void eliminarCitaPorId(int id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<cita> buscarCitasDelUsuarioPorId(int id) {
        return repositorio.buscarCitasDelUsuarioPorId(id);
    }

}
