package com.sena.citas.servicio;

import com.sena.citas.entidad.usuario;

import java.util.List;

public interface usuarioServicio {
    public List<usuario> listarTodosLosUsuarios();
    public usuario guardarUsuario(usuario usuario);
}
