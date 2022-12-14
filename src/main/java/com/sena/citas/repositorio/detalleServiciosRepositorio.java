package com.sena.citas.repositorio;

import com.sena.citas.entidad.detalleServicios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface detalleServiciosRepositorio extends JpaRepository<detalleServicios, Integer> {
    @Query("SELECT d FROM detalleServicios d WHERE d.fecha = ?1")
    List<detalleServicios>filtrarServicioPorFecha(String fecha);
    //query para actualizar el estado de las citas
    @Modifying
    @Transactional
    @Query("UPDATE detalleServicios d SET d.disponibilidad = ?1 WHERE d.id = ?2")
    int cambioDisponibilidad(boolean disponibilidad, int id);

    //listar los detalles de servicios asignados a un estilista x
    @Query("SELECT d FROM detalleServicios d WHERE d.usuario.id = ?1")
    public List<detalleServicios> detallesVinculadosAEstilista(int id);

    //listar los detalles de servicios vinculados a un servicio prestado x
    @Query("SELECT d FROM detalleServicios d WHERE d.servicioPrestado.id = ?1")
    public List<detalleServicios> detallesVinculadosAServicioPrestado(int id);


}
