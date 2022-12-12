package com.sena.citas.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class serviciosPrestados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicio_id")
    private int id;

    @Column(name = "servicio")
    private String nombreServicio;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "duracion")
    private String duracion;

    public serviciosPrestados(){

    }
    public serviciosPrestados(int id, String nombreServicio,String descripcion, String duracion){
        this.id = id;
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }
    public serviciosPrestados(String nombreServicio,String descripcion, String duracion){
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }
    //setters y getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
