package com.company.spring_boot_crud_app;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
// Anotación que indica que esta interfaz es un repositorio
@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
    // Aquí puedes definir consultas personalizadas si lo deseas
}
