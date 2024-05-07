package model.services;

import model.entities.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> getList();
    List<Producto> getByCriteria(String marca);
    Producto getById(String matricula);
    Producto save(Producto producto);
    void update(Producto producto);
    void delete(Producto producto);
}