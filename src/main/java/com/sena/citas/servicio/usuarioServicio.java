package com.sena.citas.servicio;

import com.sena.citas.entidad.usuario;
import java.util.List;

public interface usuarioServicio {
    public List<usuario> listarTodosLosUsuarios();

    public usuario guardarUsuario(usuario usuario);

    List<usuario> listarEstilistas(int nivel);

    void eliminarPorCargo(int id);

    usuario obtenerUsuarioPorId(int id);
    //eliminar usuario por id
    void eliminarUsuarioPorId(int id);
}
