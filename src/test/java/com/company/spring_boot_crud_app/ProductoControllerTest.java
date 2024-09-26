package com.company.spring_boot_crud_app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoController productoController;

    private Producto producto;

    @BeforeEach
    public void setUp() {
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto 1");
        producto.setPrecio(100.0);
    }

    @Test
    void testObtenerTodos() {
        System.out.println("Ejecutando prueba: testObtenerTodos");
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));
        ResponseEntity<List<Producto>> responseEntity = productoController.obtenerTodos();
        List<Producto> productos = responseEntity.getBody();
        
        System.out.println("Datos enviados a la API: []"); // No hay datos enviados en este caso
        System.out.println("Datos devueltos de la API: " + productos);

        Assertions.assertNotNull(productos);
        Assertions.assertEquals(1, productos.size());
        Assertions.assertEquals("Producto 1", productos.get(0).getNombre());
    }

    @Test
    void testObtenerPorId() {
        System.out.println("Ejecutando prueba: testObtenerPorId");
        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(producto));
        ResponseEntity<Producto> responseEntity = productoController.obtenerPorId(1L);
        
        System.out.println("Datos enviados a la API: ID = 1");
        System.out.println("Datos devueltos de la API: " + responseEntity.getBody());

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(producto, responseEntity.getBody());
    }

    @Test
    void testCrearProducto() {
        System.out.println("Ejecutando prueba: testCrearProducto");
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        
        System.out.println("Datos enviados a la API: " + producto);
        ResponseEntity<Producto> response = productoController.crearProducto(producto);
        System.out.println("Datos devueltos de la API: " + response.getBody());

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(producto, response.getBody());
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void testActualizarProducto() {
        System.out.println("Ejecutando prueba: testActualizarProducto");
        when(productoRepository.existsById(anyLong())).thenReturn(true);
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        
        System.out.println("Datos enviados a la API: ID = 1, " + producto);
        ResponseEntity<Producto> response = productoController.actualizarProducto(1L, producto);
        System.out.println("Datos devueltos de la API: " + response.getBody());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(producto, response.getBody());
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void testActualizarProductoNoEncontrado() {
        System.out.println("Ejecutando prueba: testActualizarProductoNoEncontrado");
        when(productoRepository.existsById(anyLong())).thenReturn(false);
        ResponseEntity<Producto> response = productoController.actualizarProducto(1L, producto);
        
        System.out.println("Datos enviados a la API: ID = 1, " + producto);
        System.out.println("Datos devueltos de la API: " + response.getStatusCode());

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testEliminarProducto() {
        System.out.println("Ejecutando prueba: testEliminarProducto");
        when(productoRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(productoRepository).deleteById(anyLong());
        ResponseEntity<Void> response = productoController.eliminarProducto(1L);
        
        System.out.println("Datos enviados a la API: ID = 1");
        System.out.println("Datos devueltos de la API: " + response.getStatusCode());

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productoRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testEliminarProductoNoEncontrado() {
        System.out.println("Ejecutando prueba: testEliminarProductoNoEncontrado");
        when(productoRepository.existsById(anyLong())).thenReturn(false);
        ResponseEntity<Void> response = productoController.eliminarProducto(1L);
        
        System.out.println("Datos enviados a la API: ID = 1");
        System.out.println("Datos devueltos de la API: " + response.getStatusCode());

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testObtenerPorCategoria() {
        System.out.println("Ejecutando prueba: testObtenerPorCategoria");
        when(categoriaRepository.existsById(anyLong())).thenReturn(true);
        when(productoRepository.findByCategoriaId(anyLong())).thenReturn(Arrays.asList(producto));
        ResponseEntity<List<Producto>> response = productoController.obtenerPorCategoria(1L);
        
        List<Producto> productos = response.getBody();
        System.out.println("Datos enviados a la API: Categoria ID = 1");
        System.out.println("Datos devueltos de la API: " + productos);

        Assertions.assertNotNull(productos);
        Assertions.assertEquals(1, productos.size());
        Assertions.assertEquals("Producto 1", productos.get(0).getNombre());
    }
}
