package com.company.spring_boot_crud_app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar operaciones CRUD sobre productos.
 * Las rutas de este controlador están prefijadas con "/api/productos".
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    /**
     * Constructor de ProductoController.
     * @param productoRepository Repositorio para interactuar con la base de datos de productos.
     */
    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Obtiene una lista de todos los productos.
     * Responde a las peticiones GET en "/api/productos".
     * @return Lista de todos los productos.
     */
    @GetMapping
    public List<Producto> obtenerTodos() {
        return (List<Producto>) productoRepository.findAll();
    }

    /**
     * Crea un nuevo producto y lo guarda en la base de datos.
     * Responde a las peticiones POST en "/api/productos".
     * @param producto Producto enviado en el cuerpo de la petición.
     * @return El producto creado con el estado HTTP 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoRepository.save(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    /**
     * Actualiza un producto existente por su ID.
     * Responde a las peticiones PUT en "/api/productos/{id}".
     * @param id ID del producto a actualizar.
     * @param producto Producto actualizado enviado en el cuerpo de la petición.
     * @return El producto actualizado o un 404 si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        producto.setId(id);
        Producto actualizado = productoRepository.save(producto);
        return ResponseEntity.ok(actualizado);
    }

    /**
     * Elimina un producto existente por su ID.
     * Responde a las peticiones DELETE en "/api/productos/{id}".
     * @param id ID del producto a eliminar.
     * @return 204 (NO CONTENT) si se elimina con éxito, o 404 si no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
