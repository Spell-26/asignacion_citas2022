package com.sena.citas.repositorio;

import com.sena.citas.entidad.cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface citaRepositorio extends JpaRepository<cita, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM cita c WHERE c.detalleS.id= ?1")
    void eliminarCitaPorDetalleId(int id);

    //citas pedidas por usuarios
    @Query("SELECT c FROM cita c WHERE c.cliente.id = ?1")
    List<cita> buscarCitasDelUsuarioPorId(int id);
}
