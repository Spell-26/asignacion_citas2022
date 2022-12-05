package com.sena.citas.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private cargo cargo;

    public usuario(){

    }
    public usuario(int id, String nombre, String apellido, String email, cargo cargo){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.cargo = cargo;
    }
    public usuario(String nombre, String apellido, String email, cargo cargo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.cargo = cargo;
    }

    //setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public cargo getCargo() {
        return cargo;
    }

    public void setCargo(cargo cargo) {
        this.cargo = cargo;
    }
}
