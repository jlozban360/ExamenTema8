package model.entities;

import java.util.Objects;

public class Producto {
    private String cod_producto;
    private String descripcion;
    private double precio;
    private int num_stock;

    public Producto( ) {

    }

    public String getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(String cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getNum_stock() {
        return num_stock;
    }

    public void setNum_stock(int num_stock) {
        this.num_stock = num_stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "cod_producto='" + cod_producto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", num_stock=" + num_stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(cod_producto, producto.cod_producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod_producto);
    }
}
