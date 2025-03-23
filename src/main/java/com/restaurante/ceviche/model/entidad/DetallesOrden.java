package com.restaurante.ceviche.model.entidad;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DetalleOrden")
public class DetallesOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_orden")
    private Integer idDetalle;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;


    // Relación muchos a uno con Orden
    @ManyToOne
    @JoinColumn(name = "id_orden", nullable = false)
    private Orden orden;

    // Relación muchos a uno con Producto
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    // Constructor vacío
    public DetallesOrden() {
    }

    // Constructor con parámetros
    public DetallesOrden(Integer cantidad, BigDecimal precioUnitario, Orden orden, Producto producto) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.orden = orden;
        this.producto = producto;
    }

    // Getters y Setters
    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
