package views;

import model.entities.Producto;
import model.services.ProductoService;
import model.services.ProductoServiceImpl;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;
import java.util.List;

public class ProductoView extends JPanel{

    ProductoService service;
    JTable listadoTable;
    private JPanel formPanel;
    JTextField _cod_producto;
    JTextField _descripcion;
    JTextField _precio;
    JTextField _numero_en_stock;
    JButton addButton;
    JButton updateButton;
    JButton deleteButton;
    JButton clearButton;

    public ProductoView () {

        service = new ProductoServiceImpl();

        this.setLayout(new GridLayout(2,1));

        listadoTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(listadoTable);
        this.add(scrollPane, BorderLayout.CENTER);

        crearFormulario();

        this.add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(true);

        listadoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = listadoTable.getSelectedRow();
                if (selectedRow >= 0) {
                    showProductoForm(selectedRow);
                }
            }
        });

        showProducto();
        this.setVisible(true);
    }

    private void crearFormulario() {
        // Crear formulario
        formPanel = new JPanel(new GridLayout(6, 2));

        formPanel.setBorder(BorderFactory.createTitledBorder("Detalles del Producto"));
        formPanel.add(new JLabel("Cod_prod:"));
        _cod_producto = new JTextField();
        formPanel.add(_cod_producto);

        formPanel.add(new JLabel("Descripcion:"));
        _descripcion = new JTextField();
        formPanel.add(_descripcion);

        formPanel.add(new JLabel("Precio:"));
        _precio = new JTextField();
        formPanel.add(_precio);

        formPanel.add(new JLabel("Num Stock:"));
        _numero_en_stock = new JTextField();
        formPanel.add(_numero_en_stock);

        addButton = new JButton("Nuevo");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProducto();
            }
        });
        formPanel.add(addButton);

        updateButton = new JButton("Actualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProducto();
            }
        });
        formPanel.add(updateButton);

        deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProducto();
            }
        });
        formPanel.add(deleteButton);

        clearButton = new JButton("Limpiar");

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        formPanel.add(clearButton);
    }

    private void showProducto() {

        // Configurar modelo de datos
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Codigo");
        model.addColumn("Descripcion");
        model.addColumn("Precio");
        model.addColumn("Stock");

        List<Producto> Productos = service.getList();

        for (Producto producto : Productos) {
            model.addRow(new Object[]{
                    producto.getCod_producto(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getNum_stock()
            });
        }

        listadoTable.setModel(model);
        formPanel.setVisible(true);
    }

    private void showProductoForm(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) listadoTable.getModel();
        String cod_producto = (String) model.getValueAt(rowIndex, 0);

        Producto producto = service.getById(cod_producto);

        _cod_producto.setText(producto.getCod_producto());
        _descripcion.setText(producto.getDescripcion());
        _precio.setText(String.valueOf(producto.getPrecio()));
        _numero_en_stock.setText(String.valueOf(producto.getNum_stock()));
    }

    private void addProducto() {

        String cod_producto = _cod_producto.getText();
        String descripcion = _descripcion.getText();
        Double precio = Double.valueOf(_precio.getText());
        int numero_en_stock = Integer.parseInt(_numero_en_stock.getText());

        Producto producto = new Producto();

        producto.setCod_producto(cod_producto);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setNum_stock(numero_en_stock);

        DefaultTableModel model = (DefaultTableModel) listadoTable.getModel();

        service.save(producto);

        model.addRow(new Object[]{
                producto.getCod_producto(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getNum_stock(),
        });

        clearForm();
    }

    private void updateProducto() {

        String cod_producto = _cod_producto.getText();
        String descripcion = _descripcion.getText();
        Double precio = Double.valueOf(_precio.getText());
        int numero_en_stock = Integer.parseInt(_numero_en_stock.getText());

        Producto producto = new Producto();

        producto.setCod_producto(cod_producto);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setNum_stock(numero_en_stock);

        service.update(producto);
        showProducto();
    }

    private void deleteProducto() {
        String nif = _cod_producto.getText();
        Producto producto = new Producto();
        producto.setCod_producto(nif);
        service.delete(producto);
        showProducto();
    }

    private void clearForm() {
        _cod_producto.setText("");
        _descripcion.setText("");
        _precio.setText("");
        _numero_en_stock.setText("");
    }
}