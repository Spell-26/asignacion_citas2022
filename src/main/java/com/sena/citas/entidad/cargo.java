package com.sena.citas.entidad;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cargos")
public class cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cargo_id")
    private int id;

    @Column(name = "nivel_cargo", nullable = false, length = 3)
    private int nivel;

    @Column(name = "info_cargo", nullable = false, length = 50)
    private String descripcion;

    public cargo(){

    }
    public cargo(int id, int nivel, String descripcion){
        this.id = id;
        this.nivel = nivel;
        this.descripcion =  descripcion;
    }
    public cargo(int nivel, String descripcion){
        this.nivel = nivel;
        this.descripcion =  descripcion;
    }

    //setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
