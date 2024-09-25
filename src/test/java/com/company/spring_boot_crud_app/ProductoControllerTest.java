package com.company.spring_boot_crud_app;

// Importaciones necesarias para las pruebas y Mockito
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach; // Para configurar el entorno de pruebas
import org.junit.jupiter.api.Test; // Para definir los métodos de prueba
import org.junit.jupiter.api.extension.ExtendWith; // Para extender el soporte de pruebas
import org.junit.jupiter.api.Assertions; // Para hacer afirmaciones en las pruebas
import org.mockito.InjectMocks; // Para inyectar mocks en la clase a probar mocks es una instancia simulada de una clase o interfaz
import org.mockito.Mock; // Para crear objetos simulados 
import org.mockito.junit.jupiter.MockitoExtension; // Para habilitar la extensión de Mockito
import org.springframework.http.HttpStatus; // Para manejar los estados HTTP
import org.springframework.http.ResponseEntity; // Para las respuestas HTTP
import java.util.Arrays; // Para trabajar con listas
import java.util.List; // Para las listas

// Indica que esta clase es una prueba de Mockito
@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    // Simulación del repositorio de productos
    @Mock
    private ProductoRepository productoRepository;

    // Controlador que se está probando, con el repositorio inyectado
    @InjectMocks
    private ProductoController productoController;

    // Variable para crear un producto de prueba
    private Producto producto;

    // Configuración inicial antes de cada prueba
    @BeforeEach
    public void setUp() {
        // Crea una nueva instancia de Producto para las pruebas
        producto = new Producto(); 
        producto.setId(1L); // Establece el ID del producto
        producto.setNombre("Producto 1"); // Establece el nombre del producto
    }

    /**
     * Prueba para obtener todos los productos.
     * Verifica que el controlador retorna una lista de productos correctamente.
     */
    @Test
    void testObtenerTodos() {
        // Configura la simulación para devolver una lista con un producto
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));
        
        // Llama al método del controlador para obtener todos los productos
        List<Producto> productos = productoController.obtenerTodos();
        
        // Verifica que el tamaño de la lista es 1
        Assertions.assertEquals(1, productos.size(), "Se debe devolver un producto");
        // Verifica que el nombre del producto es "Producto 1"
        Assertions.assertEquals("Producto 1", productos.get(0).getNombre(), "El nombre del producto debe ser 'Producto 1'");
    }

    /**
     * Prueba para crear un nuevo producto.
     * Verifica que al crear un producto, el repositorio se llame correctamente y se devuelva la respuesta adecuada.
     */
    @Test
    void testCrearProducto() {
        // Configura la simulación para guardar el producto y devolverlo
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        
        // Llama al método para crear el producto
        ResponseEntity<Producto> response = productoController.crearProducto(producto);
        
        // Verifica que el estado de la respuesta sea "CREATED" (201)
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode(), "El estado de respuesta debe ser 201 Created");
        // Verifica que el cuerpo de la respuesta sea el producto creado
        Assertions.assertEquals(producto, response.getBody(), "El cuerpo de la respuesta debe contener el producto creado");
        // Verifica que se haya llamado al método save una vez
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    /**
     * Prueba para actualizar un producto existente.
     * Verifica que al actualizar, el repositorio se llame correctamente y se devuelva la respuesta adecuada.
     */
    @Test
    void testActualizarProducto() {
        // Configura la simulación para indicar que el producto existe
        when(productoRepository.existsById(anyLong())).thenReturn(true);
        // Configura la simulación para guardar el producto y devolverlo
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        
        // Llama al método para actualizar el producto
        ResponseEntity<Producto> response = productoController.actualizarProducto(1L, producto);
        
        // Verifica que el estado de la respuesta sea "OK" (200)
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "El estado de respuesta debe ser 200 OK");
        // Verifica que el cuerpo de la respuesta sea el producto actualizado
        Assertions.assertEquals(producto, response.getBody(), "El cuerpo de la respuesta debe contener el producto actualizado");
        // Verifica que se haya llamado al método save una vez
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    /**
     * Prueba para actualizar un producto que no se encuentra.
     * Verifica que se devuelve un estado NOT FOUND cuando el producto no existe.
     */
    @Test
    void testActualizarProductoNoEncontrado() {
        // Configura la simulación para indicar que el producto no existe
        when(productoRepository.existsById(anyLong())).thenReturn(false);
        
        // Llama al método para actualizar el producto
        ResponseEntity<Producto> response = productoController.actualizarProducto(1L, producto);
        
        // Verifica que el estado de la respuesta sea "NOT FOUND" (404)
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "El estado de respuesta debe ser 404 Not Found");
    }

    /**
     * Prueba para eliminar un producto existente.
     * Verifica que al eliminar, el repositorio se llame correctamente y se devuelva la respuesta adecuada.
     */
    @Test
    void testEliminarProducto() {
        // Configura la simulación para indicar que el producto existe
        when(productoRepository.existsById(anyLong())).thenReturn(true);
        // Configura la simulación para no hacer nada al eliminar el producto
        doNothing().when(productoRepository).deleteById(anyLong());
        
        // Llama al método para eliminar el producto
        ResponseEntity<Void> response = productoController.eliminarProducto(1L);
        
        // Verifica que el estado de la respuesta sea "NO CONTENT" (204)
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode(), "El estado de respuesta debe ser 204 No Content");
        // Verifica que se haya llamado al método deleteById una vez
        verify(productoRepository, times(1)).deleteById(anyLong());
    }

    /**
     * Prueba para eliminar un producto que no se encuentra.
     * Verifica que se devuelve un estado NOT FOUND cuando el producto no existe.
     */
    @Test
    void testEliminarProductoNoEncontrado() {
        // Configura la simulación para indicar que el producto no existe
        when(productoRepository.existsById(anyLong())).thenReturn(false);
        
        // Llama al método para eliminar el producto
        ResponseEntity<Void> response = productoController.eliminarProducto(1L);
        
        // Verifica que el estado de la respuesta sea "NOT FOUND" (404)
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "El estado de respuesta debe ser 404 Not Found");
    }
}
