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

    private void show(List<usuario> usuarios){
        usuarios.forEach(System.out::println);
    }
}
