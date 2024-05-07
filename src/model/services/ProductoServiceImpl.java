package model.services;

import model.dao.ProductoDao;
import model.dao.DAOFactory;
import model.entities.Producto;

import java.util.List;

public class ProductoServiceImpl implements ProductoService {

    private ProductoDao dao;

    public ProductoServiceImpl() {
        this.dao = DAOFactory.createProductoDao();
    }

    @Override
    public List<Producto> getList() {
        return dao.findAll();
    }

    @Override
    public List<Producto> getByCriteria(String cod_producto) {
        return dao.findByCriteria(cod_producto);
    }

    @Override
    public Producto getById(String cod_producto) {
        return dao.findById(cod_producto);
    }

    @Override
    public Producto save(Producto producto) {
        return dao.save(producto);
    }

    @Override
    public void update(Producto producto) {
        dao.update(producto);
    }

    @Override
    public void delete(Producto producto) {
        dao.delete(producto);
    }
}