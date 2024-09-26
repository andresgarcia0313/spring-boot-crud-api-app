package com.company.spring_boot_crud_app;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
    List<Categoria> findByNombre(String nombre);
}