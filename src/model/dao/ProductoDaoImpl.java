package model.dao;

import datasource.DataSource;
import model.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDaoImpl extends BaseDao implements ProductoDao {

    private Connection connection;

    public ProductoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Producto> findAll() {

        List<Producto> productos = new ArrayList<Producto>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from productos order by cod_producto";
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();

            while(rs.next()){
                Producto producto = new Producto();

                producto.setCod_producto( rs.getString( "cod_producto"));
                producto.setDescripcion( rs.getString( "descripcion"));
                producto.setPrecio( rs.getDouble( "precio"));
                producto.setNum_stock( rs.getInt( "numero_en_stock"));

                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.closeStatement(ps);
            DataSource.closeResultSet(rs);
        }
        return productos;
    }

    @Override
    public List<Producto> findByCriteria(String cod_producto) {
        List<Producto> productos = new ArrayList<Producto>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from productos where cod_producto = ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, cod_producto);

            rs = ps.executeQuery();

            while(rs.next()){
                Producto producto = new Producto();

                producto.setCod_producto( rs.getString( "cod_producto"));
                producto.setDescripcion( rs.getString( "descripcion"));
                producto.setPrecio( rs.getDouble( "precio"));
                producto.setNum_stock( rs.getInt( "numero_en_stock"));

                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.closeStatement(ps);
            DataSource.closeResultSet(rs);
        }
        return productos;
    }

    @Override
    public Producto findById(String cod_producto)
    {
        Producto producto = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from productos where cod_producto = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, cod_producto);

            rs = ps.executeQuery();

            if(rs.next()){
                producto = new Producto();
                producto.setCod_producto( rs.getString( "cod_producto"));
                producto.setDescripcion( rs.getString( "descripcion"));
                producto.setPrecio( rs.getDouble( "precio"));
                producto.setNum_stock( rs.getInt( "numero_en_stock"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.closeStatement(ps);
            DataSource.closeResultSet(rs);
        }
        return producto;
    }

    @Override
    public Producto save(Producto producto) {

        PreparedStatement ps = null;

        try {
            String sql = "insert into productos (cod_producto, descripcion, precio, numero_en_stock) values (?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, producto.getCod_producto());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getNum_stock());

            int fila = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.closeStatement(ps);
        }

        return producto;
    }

    @Override
    public void update(Producto producto) {

        PreparedStatement ps = null;

        try {
            String sql = "update productos set cod_producto = ? , descripcion = ?, precio = ?, numero_en_stock = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, producto.getCod_producto());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getNum_stock());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.closeStatement(ps);
        }
    }

    @Override
    public void delete(Producto producto) {
        PreparedStatement ps = null;

        try {
            String sql = "delete from productos where cod_producto = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, producto.getCod_producto());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.closeStatement(ps);
        }
    }
}