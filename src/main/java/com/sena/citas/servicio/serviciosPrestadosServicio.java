package com.sena.citas.servicio;


import com.sena.citas.entidad.serviciosPrestados;

import java.util.List;

public interface serviciosPrestadosServicio {
    public List<serviciosPrestados> listarTodosLosServiciosPrestados();
    public serviciosPrestados guardarDServiciosPrestados(serviciosPrestados serviciosP);
}
