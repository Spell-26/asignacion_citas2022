package com.sena.citas.servicio;

import com.sena.citas.entidad.cargo;
import com.sena.citas.repositorio.cargoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cargoServicioImpl implements cargoServicio{
    @Autowired
    private cargoRepositorio repositorio;

    @Override
    public List<cargo> listarTodosLosCargos() {
        return repositorio.findAll();
    }

    @Override
    public cargo guardarCargo(cargo cargo) {
        return repositorio.save(cargo);
    }

    @Override
    public cargo obtenerCargoPorId(int id) {
        return repositorio.findById(id).get();
    }
}
