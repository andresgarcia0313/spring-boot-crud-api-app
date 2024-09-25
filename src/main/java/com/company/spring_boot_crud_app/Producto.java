package com.company.spring_boot_crud_app;

import jakarta.persistence.*;

// Esta es la clase de entidad que representa un Producto
@Entity
@Table(name = "productos") // Indica el nombre de la tabla en la base de datos
public class Producto {

    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se auto-genera el ID
    private Long id;

    @Column(nullable = false) // Esta columna no puede ser nula
    private String nombre;

    @Column(nullable = false) // Esta columna no puede ser nula
    private Double precio; // Agregamos el campo "precio"

    // Constructor vacío
    public Producto() {
    }

    // Constructor con parámetros
    public Producto(String nombre, Double precio) { // Incluimos "precio" en el constructor
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() { // Getter para "precio"
        return precio;
    }

    public void setPrecio(Double precio) { // Setter para "precio"
        this.precio = precio;
    }
}
