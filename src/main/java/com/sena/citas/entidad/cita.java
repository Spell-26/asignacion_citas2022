package com.sena.citas.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "citas")
public class cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cita_id")
    private int id;


}
