#!/bin/bash

# Script para probar la API de productos y categorías

# URLs base de la API
PRODUCTOS_URL="http://localhost:8080/api/productos"
CATEGORIAS_URL="http://localhost:8080/api/categorias"

# Función para imprimir separadores
print_separator() {
    echo "----------------------------------------"
}

# 1. Crear una categoría (necesaria para crear productos)
echo "Creando categoría..."
curl -X POST $CATEGORIAS_URL -H "Content-Type: application/json" -d '{"nombre": "Electrónicos"}'
echo -e "\n"
print_separator

# 2. Crear otra categoría (opcional)
echo "Creando otra categoría (opcional)..."
# curl -X POST $CATEGORIAS_URL -H "Content-Type: application/json" -d '{"nombre": "Ropa"}'
# echo -e "\n"

# 3. Obtener todas las categorías (verificar la creada)
echo "Obteniendo todas las categorías..."
curl -X GET $CATEGORIAS_URL
echo -e "\n"
print_separator

# 4. Crear nuevos productos con la categoría creada
echo "Creando nuevos productos..."
curl -X POST $PRODUCTOS_URL -H "Content-Type: application/json" \
-d '{"nombre": "Smartphone", "precio": 599.99, "categoria": {"id": 1}}'  # Referencia a la categoría creada (ID 1)
echo -e "\n"
# curl -X POST $PRODUCTOS_URL -H "Content-Type: application/json" \  # Opcional si se creó la segunda categoría
# -d '{"nombre": "Camiseta", "precio": 29.99, "categoria": {"id": 2}}'  # Referencia a la segunda categoría (ID 2)
echo -e "\n"
print_separator

# 5. Obtener todos los productos (verificar los creados)
echo "Obteniendo todos los productos..."
curl -X GET $PRODUCTOS_URL
echo -e "\n"
print_separator

# 6. Intentar eliminar la categoría con productos asociados (debería fallar)
echo "Intentando eliminar la categoría 1 (debería fallar)..."
curl -X DELETE $CATEGORIAS_URL/1
echo -e "\n"
print_separator

# 7. (Opcional) Actualizar un producto
# echo "Actualizando el producto con ID 1..."
# curl -X PUT $PRODUCTOS_URL/1 -H "Content-Type: application/json" \
# -d '{"nombre": "Smartphone actualizado", "precio": 649.99, "categoria": {"id": 1}}'
# echo -e "\n"
# print_separator

# 8. Obtener un producto específico
echo "Obteniendo el producto con ID 1..."
curl -X GET $PRODUCTOS_URL/1
echo -e "\n"
print_separator

# 9. Eliminar un producto
echo "Eliminando el producto con ID 1..."  # Eliminar un producto creado
curl -X DELETE $PRODUCTOS_URL/1
echo -e "\n"
print_separator

# 10. Obtener todos los productos después de la eliminación
echo "Obteniendo todos los productos después de la eliminación..."
curl -X GET $PRODUCTOS_URL
echo -e "\n"
print_separator

echo "Pruebas completadas."