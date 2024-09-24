package com.company.spring_boot_crud_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Anotación que indica que esta clase es un controlador REST
@RestController
@RequestMapping("/api/productos") // Prefijo para las rutas de este controlador
public class ProductoController {

    @Autowired // Inyección de dependencia del repositorio
    private ProductoRepository productoRepository;

    // Método para obtener todos los productos
    @GetMapping
    public List<Producto> obtenerTodos() {
        return (List<Producto>) productoRepository.findAll();
    }

    // Método para crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoRepository.save(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    // Método para actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra
        }
        producto.setId(id);
        Producto actualizado = productoRepository.save(producto);
        return ResponseEntity.ok(actualizado);
    }

    // Método para eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra
        }
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 sin contenido
    }
}
