package com.company.spring_boot_crud_app;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase de entidad que representa un Producto en la base de datos.
 * Un producto tiene un ID, un nombre y un precio.
 */
@Entity
@Table(name = "productos") // Mapea esta entidad a la tabla "productos" en la base de datos
public class Producto {

    /**
     * Campo ID que actúa como clave primaria en la tabla de la base de datos.
     * Se auto-genera con una estrategia de identidad (autoincremental).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Campo para almacenar el nombre del producto.
     * Esta columna no puede ser nula (nullable = false).
     */
    @Column(nullable = false)
    private String nombre;

    /**
     * Campo para almacenar el precio del producto.
     * Esta columna no puede ser nula (nullable = false).
     */
    @Column(nullable = false)
    private Double precio;

    /**
     * Constructor vacío requerido por JPA.
     * JPA (Java Persistence API) necesita un constructor sin parámetros para poder
     * instanciar la entidad.
     */
    public Producto() {
    }

    /**
     * Constructor que permite crear un Producto con un nombre y precio.
     * 
     * @param nombre El nombre del producto.
     * @param precio El precio del producto.
     */
    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Obtiene el ID del producto.
     * 
     * @return El ID del producto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del producto.
     * 
     * @param id El ID del producto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombre El nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return El precio del producto.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precio El precio del producto.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
