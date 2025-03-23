package com.restaurante.ceviche.model.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "Orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Integer idOrden;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "numero_mesa", nullable = false)
    private Integer numeroMesa;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    // Relación muchos a uno con Cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    // Relación muchos a uno con Trabajador
    @ManyToOne
    @JoinColumn(name = "id_trabajador", nullable = false)
    private Trabajador trabajador;

    // Constructor vacío
    public Orden() {
        this.fecha = LocalDateTime.now();
        this.total = BigDecimal.ZERO;
    }

    // Constructor con parámetros
    public Orden(LocalDateTime fecha, Integer numeroMesa, Cliente cliente, Trabajador trabajador, BigDecimal total) {
        this.fecha = fecha;
        this.numeroMesa = numeroMesa;
        this.cliente = cliente;
        this.trabajador = trabajador;
        this.total = total;
    }

    // Getters y Setters
    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
}
