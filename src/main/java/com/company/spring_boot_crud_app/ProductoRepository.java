package com.company.spring_boot_crud_app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
    List<Producto> findByNombre(String nombre);

    List<Producto> findByCategoriaId(Long categoriaId);

    List<Producto> findByPrecioGreaterThan(Double precio);

    List<Producto> findByPrecioLessThan(Double precio);

}
