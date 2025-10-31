package co.edu.uniquindio.SOLID.Controlador;

import co.edu.uniquindio.SOLID.Model.DTO.ProductoDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Model.EntradaInventario;
import co.edu.uniquindio.SOLID.Model.Minimercado;
import co.edu.uniquindio.SOLID.Model.Producto;
import co.edu.uniquindio.SOLID.Model.Proveedor;
import co.edu.uniquindio.SOLID.Service.Fachadas.InventarioFacade;
import co.edu.uniquindio.SOLID.Service.Fachadas.MinimercadoFacade;
import co.edu.uniquindio.SOLID.Service.Fachadas.ProveedorFacade;
import co.edu.uniquindio.SOLID.utils.Mappers.ProveedorMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class InventarioController implements Initializable {

    @FXML private ComboBox<ProveedorDTO> cmbProveedores;
    @FXML private TitledPane tpCrearProveedor;
    @FXML private TextField txtProvNit;
    @FXML private TextField txtProvNombre;
    @FXML private TextField txtProvContacto;
    @FXML private TextField txtProvEmail;
    @FXML private TextField txtProvTelefono;
    @FXML private ComboBox<ProductoDTO> cmbProductoEntrada;
    @FXML private Spinner<Integer> spnCantidadEntrada;
    @FXML private Label lblResultadoEntrada;
    @FXML private TableView<ProductoDTO> tblProductosInv;
    @FXML private TableColumn<ProductoDTO, String> colInvSku;
    @FXML private TableColumn<ProductoDTO, String> colInvNombre;
    @FXML private TableColumn<ProductoDTO, Number> colInvPrecio;
    @FXML private TableColumn<ProductoDTO, Number> colInvStock;

    private ObservableList<ProveedorDTO> proveedores;
    private ObservableList<ProductoDTO> productos;
    private MinimercadoFacade minimercadoFacade;
    private InventarioFacade inventarioFacade;
    private ProveedorFacade proveedorFacade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        minimercadoFacade=new MinimercadoFacade();
        inventarioFacade= new  InventarioFacade();
        proveedorFacade= new  ProveedorFacade();
        proveedores = FXCollections.observableArrayList(minimercadoFacade.obtenerTodosLosProveedores());
        productos = FXCollections.observableArrayList(minimercadoFacade.obtenerTodosLosProductos());
        
        if (cmbProveedores != null) {
            cmbProveedores.setItems(proveedores);
            cmbProveedores.getSelectionModel().selectedItemProperty().addListener((obs, anterior, seleccionado) -> {
                if (seleccionado != null) {
                    if (txtProvNit != null) txtProvNit.setText(seleccionado.getNit());
                    if (txtProvNombre != null) txtProvNombre.setText(seleccionado.getNombre());
                    if (txtProvContacto != null) txtProvContacto.setText(seleccionado.getContacto());
                    if (txtProvEmail != null) txtProvEmail.setText(seleccionado.getEmail());
                    if (txtProvTelefono != null) txtProvTelefono.setText(seleccionado.getTelefono());
                }
            });
        }
        if (cmbProductoEntrada != null) {
            cmbProductoEntrada.setItems(productos);
        }
        if (spnCantidadEntrada != null) {
            spnCantidadEntrada.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
        }
        if (tblProductosInv != null) {
            colInvSku.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getSku()));
            colInvNombre.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getNombre()));
            colInvPrecio.setCellValueFactory(cd -> new javafx.beans.property.SimpleDoubleProperty(cd.getValue().getPrecio()));
            colInvStock.setCellValueFactory(cd -> new javafx.beans.property.SimpleIntegerProperty(cd.getValue().getStock()));
            tblProductosInv.setItems(productos);
        }
        if (tpCrearProveedor != null) tpCrearProveedor.setExpanded(false);
    }

    @FXML
    void mostrarCrearProveedor() {
        if (tpCrearProveedor != null) {
            tpCrearProveedor.setExpanded(!tpCrearProveedor.isExpanded());
        }
    }

    @FXML
    void crearProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        String nombre = txtProvNombre != null ? txtProvNombre.getText() : null;
        String contacto = txtProvContacto != null ? txtProvContacto.getText() : "";
        String email = txtProvEmail != null ? txtProvEmail.getText() : "";
        String telefono = txtProvTelefono != null ? txtProvTelefono.getText() : "";
        
        // Validaciones de campos
        if (nit == null || nit.trim().isEmpty()) {
            mostrarError("El NIT es obligatorio");
            return;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            mostrarError("El nombre es obligatorio");
            return;
        }
        
        try {
            ProveedorDTO nuevoProveedorDTO= new ProveedorDTO(nit, nombre, contacto, email, telefono);
            if(minimercadoFacade.crearProveedor(nuevoProveedorDTO)){
                proveedores.add(nuevoProveedorDTO);
                if (cmbProveedores != null) cmbProveedores.setItems(proveedores); //-> Se agrega esta línea para refrescar la visual del dropdown porque no muestra el nuevo proveedor creado.
                if (cmbProveedores != null) cmbProveedores.getSelectionModel().select(nuevoProveedorDTO);
                if (lblResultadoEntrada != null) lblResultadoEntrada.setText("Proveedor creado: " + nombre);
                if (tpCrearProveedor != null) tpCrearProveedor.setExpanded(false);
                if (txtProvNit != null) txtProvNit.clear();
                if (txtProvNombre != null) txtProvNombre.clear();
                if (txtProvContacto != null) txtProvContacto.clear();
                if (txtProvEmail != null) txtProvEmail.clear();
                if (txtProvTelefono != null) txtProvTelefono.clear();
            }
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    void actualizarProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        String nombre = txtProvNombre != null ? txtProvNombre.getText() : null;
        String contacto = txtProvContacto != null ? txtProvContacto.getText() : null;
        String email = txtProvEmail != null ? txtProvEmail.getText() : null;
        String telefono = txtProvTelefono != null ? txtProvTelefono.getText() : null;
        if (nit == null || nit.trim().isEmpty()) { mostrarError("El NIT es obligatorio"); return; }
        try {
            ProveedorDTO proveedorDTOActualizado=  new ProveedorDTO(nit, nombre, contacto, email, telefono);
            for (int i = 0; i < proveedores.size(); i++) {
                if (proveedores.get(i).getNit().equals(nit)) { proveedores.set(i, proveedorDTOActualizado); break; }
            }
            if (cmbProveedores != null) cmbProveedores.setItems(FXCollections.observableArrayList(proveedores));
        } catch (IllegalArgumentException e) { mostrarError(e.getMessage()); }
    }

    @FXML
    void eliminarProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        if (nit == null || nit.trim().isEmpty()) { mostrarError("El NIT es obligatorio"); return; }
        try {
            proveedorFacade.eliminarProveedor(nit);
            proveedores.removeIf(p -> p.getNit().equals(nit));
            if (cmbProveedores != null) cmbProveedores.setItems(FXCollections.observableArrayList(proveedores));
        } catch (IllegalArgumentException e) { mostrarError(e.getMessage()); }
    }

    @FXML
    void activarProveedor() {
        //Se elimina el FOR porque la busqueda del proveedor por Id se delega al metodo activarProveedor
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        if (nit == null || nit.trim().isEmpty()) {
            mostrarError("El NIT es obligatorio");
            return;
        }
        try {
            proveedorFacade.activarProveedor(nit);
            proveedores.setAll(minimercadoFacade.obtenerTodosLosProveedores());
            if (cmbProveedores != null) cmbProveedores.setItems(proveedores);
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    //Se elimina el FOR porque la busqueda del proveedor por Id se delega al metodo inactivarProveedor
    void inactivarProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        if (nit == null || nit.trim().isEmpty()) { mostrarError(
                "El NIT es obligatorio"); return; }
        try {
            proveedorFacade.inactivarProveedor(nit);
            proveedores.setAll(minimercadoFacade.obtenerTodosLosProveedores());
            if (cmbProveedores != null) cmbProveedores.setItems(proveedores);
        } catch (IllegalArgumentException e) { mostrarError(e.getMessage()); }
    }

    @FXML
    void confirmarEntradaInventario() {/*
        Proveedor proveedor = cmbProveedores != null ? cmbProveedores.getValue() : null;
        Producto prod = cmbProductoEntrada != null ? cmbProductoEntrada.getValue() : null;
        Integer cant = spnCantidadEntrada != null ? spnCantidadEntrada.getValue() : 0;
        
        // Validaciones de campos
        if (proveedor == null) {
            mostrarError("Seleccione un proveedor");
            return;
        }
        if (prod == null) {
            mostrarError("Seleccione un producto");
            return;
        }
        if (cant == null || cant <= 0) {
            mostrarError("Cantidad inválida");
            return;
        }
        
        try {
            inventario.registrarEntradaInventario(proveedor, prod, cant);
            if (lblResultadoEntrada != null) lblResultadoEntrada.setText("Entrada confirmada. Stock " + prod.getSku() + ": " + prod.getStock());
            if (tblProductosInv != null) tblProductosInv.refresh();
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }*/
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}


