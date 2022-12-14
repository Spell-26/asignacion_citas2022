package com.sena.citas.repositorio;

import com.sena.citas.entidad.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface usuarioRepositorio extends JpaRepository<usuario, Integer> {
    @Query("SELECT u FROM usuario u WHERE u.cargo.nivel = ?1")
    List<usuario> listarEstilistas(int nivel);

    @Modifying
    @Transactional
    @Query("DELETE FROM usuario u WHERE u.cargo.id = ?1")
    void eliminarPorCargo(int id);
}
