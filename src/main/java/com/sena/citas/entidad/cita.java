package com.sena.citas.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "citas")
public class cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cita_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private usuario cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalle_servicio_id")
    private detalleServicios detalleS;

    public cita() {
    }

    public cita(usuario cliente, detalleServicios detalleS) {
        this.cliente = cliente;
        this.detalleS = detalleS;
    }

    public cita(int id, usuario cliente, detalleServicios detalleS) {
        this.id = id;
        this.cliente = cliente;
        this.detalleS = detalleS;
    }

    //getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public usuario getCliente() {
        return cliente;
    }

    public void setCliente(usuario cliente) {
        this.cliente = cliente;
    }

    public detalleServicios getDetalleS() {
        return detalleS;
    }

    public void setDetalleS(detalleServicios detalleS) {
        this.detalleS = detalleS;
    }
}
