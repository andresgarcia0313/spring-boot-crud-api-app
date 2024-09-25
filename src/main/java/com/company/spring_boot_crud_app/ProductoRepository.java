package com.company.spring_boot_crud_app;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
// Anotación que indica que esta interfaz es un repositorio
@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
    // Encuentra productos por su nombre
    List<Producto> findByNombre(String nombre);

    // Encuentra productos cuyo precio sea mayor que un valor dado
    List<Producto> findByPrecioGreaterThan(Double precio);

    // Encuentra productos cuyo precio sea menor que un valor dado
    List<Producto> findByPrecioLessThan(Double precio);
    
}
