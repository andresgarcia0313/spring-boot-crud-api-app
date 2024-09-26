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
class CategoriaControllerTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private CategoriaController categoriaController;

    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Categoría 1");
    }

    @Test
    void testObtenerTodas() {
        System.out.println("Ejecutando prueba: testObtenerTodas");
        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria));
        ResponseEntity<List<Categoria>> responseEntity = categoriaController.obtenerTodas();
        
        List<Categoria> categorias = responseEntity.getBody();
        System.out.println("Datos enviados a la API: []"); // No hay datos enviados en este caso
        System.out.println("Datos devueltos de la API: " + categorias);

        Assertions.assertNotNull(categorias);
        Assertions.assertEquals(1, categorias.size());
        Assertions.assertEquals("Categoría 1", categorias.get(0).getNombre());
    }

    @Test
    void testObtenerPorId() {
        System.out.println("Ejecutando prueba: testObtenerPorId");
        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.of(categoria));
        ResponseEntity<Categoria> responseEntity = categoriaController.obtenerPorId(1L);
        
        System.out.println("Datos enviados a la API: ID = 1");
        System.out.println("Datos devueltos de la API: " + responseEntity.getBody());

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(categoria, responseEntity.getBody());
    }

    @Test
    void testCrearCategoria() {
        System.out.println("Ejecutando prueba: testCrearCategoria");
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);
        
        System.out.println("Datos enviados a la API: " + categoria);
        ResponseEntity<Categoria> response = categoriaController.crearCategoria(categoria);
        System.out.println("Datos devueltos de la API: " + response.getBody());

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(categoria, response.getBody());
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void testActualizarCategoria() {
        System.out.println("Ejecutando prueba: testActualizarCategoria");
        when(categoriaRepository.existsById(anyLong())).thenReturn(true);
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);
        
        System.out.println("Datos enviados a la API: ID = 1, " + categoria);
        ResponseEntity<Categoria> response = categoriaController.actualizarCategoria(1L, categoria);
        System.out.println("Datos devueltos de la API: " + response.getBody());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(categoria, response.getBody());
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void testActualizarCategoriaNoEncontrada() {
        System.out.println("Ejecutando prueba: testActualizarCategoriaNoEncontrada");
        when(categoriaRepository.existsById(anyLong())).thenReturn(false);
        ResponseEntity<Categoria> response = categoriaController.actualizarCategoria(1L, categoria);
        
        System.out.println("Datos enviados a la API: ID = 1, " + categoria);
        System.out.println("Datos devueltos de la API: " + response.getStatusCode());

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testEliminarCategoria() {
        System.out.println("Ejecutando prueba: testEliminarCategoria");
        when(categoriaRepository.existsById(anyLong())).thenReturn(true);
        when(productoRepository.findByCategoriaId(anyLong())).thenReturn(Arrays.asList());
        doNothing().when(categoriaRepository).deleteById(anyLong());
        
        System.out.println("Datos enviados a la API: ID = 1");
        ResponseEntity<Void> response = categoriaController.eliminarCategoria(1L);
        System.out.println("Datos devueltos de la API: " + response.getStatusCode());

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoriaRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testEliminarCategoriaConProductosAsociados() {
        System.out.println("Ejecutando prueba: testEliminarCategoriaConProductosAsociados");
        when(categoriaRepository.existsById(anyLong())).thenReturn(true);
        when(productoRepository.findByCategoriaId(anyLong())).thenReturn(Arrays.asList(new Producto()));
        
        System.out.println("Datos enviados a la API: ID = 1");
        ResponseEntity<Void> response = categoriaController.eliminarCategoria(1L);
        System.out.println("Datos devueltos de la API: " + response.getStatusCode());

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testObtenerProductosPorCategoria() {
        System.out.println("Ejecutando prueba: testObtenerProductosPorCategoria");
        when(categoriaRepository.existsById(anyLong())).thenReturn(true);
        when(productoRepository.findByCategoriaId(anyLong())).thenReturn(Arrays.asList(new Producto()));
        
        System.out.println("Datos enviados a la API: Categoria ID = 1");
        ResponseEntity<List<Producto>> response = categoriaController.obtenerProductosPorCategoria(1L);
        System.out.println("Datos devueltos de la API: " + response.getStatusCode());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
