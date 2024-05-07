package model.dao;

import datasource.DataSource;

public class DAOFactory {

    public static ProductoDao createProductoDao(){
        return new ProductoDaoImpl(DataSource.getConnection());
    }

}