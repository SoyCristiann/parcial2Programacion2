package co.edu.uniquindio.SOLID.Controlador;

import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;
import co.edu.uniquindio.SOLID.Model.Empleado;
import co.edu.uniquindio.SOLID.Model.Minimercado;
import co.edu.uniquindio.SOLID.Service.EmpleadoService;
import co.edu.uniquindio.SOLID.Service.Fachadas.EmpresaAdminFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class EmpleadosController implements Initializable {

    @FXML private TextField txtEmpId;
    @FXML private TextField txtEmpNombre;
    @FXML private ComboBox<String> cmbEmpRol;
    @FXML private TableView<EmpleadoDTO> tblEmpleados;
    @FXML private TableColumn<EmpleadoDTO, String> colEmpId;
    @FXML private TableColumn<EmpleadoDTO, String> colEmpNombre;
    @FXML private TableColumn<EmpleadoDTO, String> colEmpRol;
    @FXML private TableColumn<EmpleadoDTO, String> colEmpEstado;

    private ObservableList<EmpleadoDTO> empleados;
    private Minimercado minimercado = Minimercado.getInstancia();
    private EmpresaAdminFacade empresaAdminFacade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        empresaAdminFacade= new EmpresaAdminFacade();
        empleados = FXCollections.observableArrayList(empresaAdminFacade.listarEmpleados());
        
        if (cmbEmpRol != null) {
            cmbEmpRol.setItems(FXCollections.observableArrayList("CAJERO", "BODEGUERO"));
            cmbEmpRol.setValue("CAJERO");
        }
        if (tblEmpleados != null) {
            colEmpId.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getId()));
            colEmpNombre.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getNombre()));
            colEmpRol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getRol().name()));
            colEmpEstado.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().isActivo() ? "Activo" : "Inactivo"));
            tblEmpleados.setItems(empleados);
        }
    }

    @FXML
    void crearEmpleado() {
        String id = txtEmpId != null ? txtEmpId.getText() : null;
        String nombre = txtEmpNombre != null ? txtEmpNombre.getText() : null;
        String rol = cmbEmpRol != null ? cmbEmpRol.getValue() : null;
        
        // Validaciones de campos
        if (id == null || id.trim().isEmpty()) {
            mostrarError("El ID es obligatorio");
            return;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            mostrarError("El nombre es obligatorio");
            return;
        }
        if (rol == null) {
            mostrarError("El rol es obligatorio");
            return;
        }
        
        try {
            EmpleadoDTO nuevoEmpleadoDTO = new EmpleadoDTO(id, nombre, EmpleadoDTO.Rol.valueOf(rol), true);
            if(empresaAdminFacade.crearEmpleado(nuevoEmpleadoDTO)){
                System.out.println("respuesta de creación del empleado: " + nuevoEmpleadoDTO);
                empleados.add(nuevoEmpleadoDTO);
                if (tblEmpleados != null) tblEmpleados.refresh();
                if (txtEmpId != null) txtEmpId.clear();
                if (txtEmpNombre != null) txtEmpNombre.clear();
                if (cmbEmpRol != null) cmbEmpRol.setValue("CAJERO");
                ObservableList<EmpleadoDTO> nuevaLista = FXCollections.observableArrayList(
                        empresaAdminFacade.listarEmpleados()
                );
                if (tblEmpleados != null) {
                    tblEmpleados.setItems(nuevaLista);
                }
            }
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    void actualizarEmpleado() {
        String id = txtEmpId != null ? txtEmpId.getText() : null;
        String nombre = txtEmpNombre != null ? txtEmpNombre.getText() : null;
        String rol = cmbEmpRol != null ? cmbEmpRol.getValue() : null;
        if (id == null || id.trim().isEmpty()) {
            mostrarError("El ID es obligatorio");
            return;
        }
        try {
            EmpleadoDTO empleadoActualizadoDTO= new EmpleadoDTO(id, nombre, EmpleadoDTO.Rol.valueOf(rol), true);
            if(empresaAdminFacade.actualizarEmpleado(empleadoActualizadoDTO)){
                for (int i = 0; i < empleados.size(); i++) {
                    if (empleados.get(i).getId().equals(id)) { empleados.set(i, empleadoActualizadoDTO); break; }
                }
                if (tblEmpleados != null) {
                    tblEmpleados.refresh();
                }
                ObservableList<EmpleadoDTO> nuevaLista = FXCollections.observableArrayList(
                        empresaAdminFacade.listarEmpleados()
                );
                if (tblEmpleados != null) {
                    tblEmpleados.setItems(nuevaLista);
                }
            }
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    void eliminarEmpleado() {
        String id = txtEmpId != null ? txtEmpId.getText() : null;
        if (id == null || id.trim().isEmpty()) {
            mostrarError("El ID es obligatorio");
            return;
        }
        try {
            if(empresaAdminFacade.eliminarEmpleado(id)){
                if (tblEmpleados != null) {
                    tblEmpleados.refresh();
                }
                ObservableList<EmpleadoDTO> nuevaLista = FXCollections.observableArrayList(
                        empresaAdminFacade.listarEmpleados()
                );
                if (tblEmpleados != null) {
                    tblEmpleados.setItems(nuevaLista);
                }
            }
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    void activarEmpleado() {
        //Se ajusta el metodo para que al realizar cambio, se actualice y/o refresque la tabla
        String id = txtEmpId != null ? txtEmpId.getText() : null;
        if (id == null || id.trim().isEmpty()) {
            mostrarError("El ID es obligatorio");
            return;
        }
        try {
            empresaAdminFacade.activarEmpleado(id);
            ObservableList<EmpleadoDTO> nuevaLista = FXCollections.observableArrayList(
                    empresaAdminFacade.listarEmpleados()
            );
            if (tblEmpleados != null) {
                tblEmpleados.setItems(nuevaLista);
            }
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        } catch (Exception e) {
            mostrarError("Error al intentar activar el empleado.");
        }
    }

    @FXML
    void inactivarEmpleado() {
        //Se ajusta el metodo para que al realizar cambio, se actualice y/o refresque la tabla
        String id = txtEmpId != null ? txtEmpId.getText() : null;
        if (id == null || id.trim().isEmpty()) {
            mostrarError("El ID es obligatorio");
            return;
        }
        try {
            empresaAdminFacade.inactivarEmpleado(id);
            ObservableList<EmpleadoDTO> nuevaLista = FXCollections.observableArrayList(
                    empresaAdminFacade.listarEmpleados()
            );
            if (tblEmpleados != null) {
                tblEmpleados.setItems(nuevaLista);
            }
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        } catch (Exception e) {
            mostrarError("Error al intentar inactivar el empleado.");
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}


