package model.dao;

import model.entities.Producto;

import java.util.List;

public interface ProductoDao {
    List<Producto> findAll();
    List<Producto> findByCriteria(String cod_producto);
    Producto findById(String nif);
    Producto save(Producto producto);
    void update(Producto producto);
    void delete(Producto producto);
}