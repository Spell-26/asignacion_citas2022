package com.sena.citas.entidad;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


@Entity
@Table(name = "detalle_servicios")
public class detalleServicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dservicio_id")
    private int id;

    @Column(name = "fecha")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private String fecha;

    @Column(name = "hora")
    private String hora;

    @Column(name = "disponibilidad", columnDefinition = "boolean default false")
    private boolean disponibilidad;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private usuario usuario;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private serviciosPrestados servicioPrestado;

    @OneToOne(mappedBy = "detalleS")
    private cita cita;

    public detalleServicios(){

    }
    public detalleServicios(int id, usuario  usuario, serviciosPrestados servicio, String fecha, boolean disponibilidad, String hora){
        this.id = id;
        this.usuario = usuario;
        this.servicioPrestado = servicio;
        this.fecha = fecha;
        this.disponibilidad = disponibilidad;
        this.hora = hora;
    }
    public detalleServicios(usuario  usuario, serviciosPrestados servicio, String fecha, boolean disponibilidad, String hora){
        this.usuario = usuario;
        this.servicioPrestado = servicio;
        this.fecha = fecha;
        this.disponibilidad = disponibilidad;
        this.hora = hora;
    }
    //getters and setters


    public com.sena.citas.entidad.cita getCita() {
        return cita;
    }

    public void setCita(com.sena.citas.entidad.cita cita) {
        this.cita = cita;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
