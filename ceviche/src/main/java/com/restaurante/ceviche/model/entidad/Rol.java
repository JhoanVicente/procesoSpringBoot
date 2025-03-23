package com.restaurante.ceviche.model.entidad;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "tipo_rol", nullable = false, unique = true, length = 50)
    private String tipoRol; // Ahora tipoRol es un String en lugar de un Enum

    // Relación uno a muchos con Usuario
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> usuarios;

    // Constructor vacío
    public Rol() {
    }

    // Constructor con parámetros
    public Rol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    // Getters y Setters
    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
