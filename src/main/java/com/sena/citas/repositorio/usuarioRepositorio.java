package com.sena.citas.repositorio;

import com.sena.citas.entidad.usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usuarioRepositorio extends JpaRepository<usuario, Integer> {
}
