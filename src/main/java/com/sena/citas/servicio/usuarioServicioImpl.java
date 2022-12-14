package com.sena.citas.servicio;

import com.sena.citas.entidad.usuario;
import com.sena.citas.repositorio.usuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class usuarioServicioImpl implements usuarioServicio{
    @Autowired
    private usuarioRepositorio repositorio;
    @Override
    public List<usuario> listarTodosLosUsuarios() {
        return repositorio.findAll();
    }

    @Override
    public usuario guardarUsuario(usuario usuario) {
        return repositorio.save(usuario);
    }

    @Override
    public List<usuario> listarEstilistas(int nivel) {
        return repositorio.listarEstilistas(nivel);
    }

    @Override
    public void eliminarPorCargo(int id) {
        repositorio.eliminarPorCargo(id);
    }

    @Override
    public usuario obtenerUsuarioPorId(int id) {
        return repositorio.findById(id).get();
    }
    @Override
    public void eliminarUsuarioPorId(int id) {
        repositorio.deleteById(id);
    }
}
