package com.sena.citas.entidad;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "detalle_servicios")
public class detalleServicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dservicio_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private usuario usuario;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private serviciosPrestados servicioPrestado;

    public detalleServicios(){

    }
    public detalleServicios(int id, usuario  usuario, serviciosPrestados servicio){
        this.id = id;
        this.usuario = usuario;
        this.servicioPrestado = servicio;
    }
    public detalleServicios(usuario  usuario, serviciosPrestados servicio){
        this.usuario = usuario;
        this.servicioPrestado = servicio;
    }
    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }

    public serviciosPrestados getServicioPrestado() {
        return servicioPrestado;
    }

    public void setServicioPrestado(serviciosPrestados servicioPrestado) {
        this.servicioPrestado = servicioPrestado;
    }
}
