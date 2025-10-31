package co.edu.uniquindio.SOLID.Service.Fachadas;

import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Service.EmpleadoService;
import co.edu.uniquindio.SOLID.Service.ProveedorService;

import java.util.List;

public class ProveedorFacade {

    private final ProveedorService proveedorService;

    public ProveedorFacade() {
        this.proveedorService = new ProveedorService();
    }

    //Proveedor
    public boolean crearProveedor(ProveedorDTO proveedor) {
        return proveedorService.crearProveedor(proveedor);
    }

    public boolean actualizarProveedor(ProveedorDTO proveedor) {
        return proveedorService.actualizarProveedor(proveedor);
    }

    public boolean eliminarProveedor(String nit) {
        return proveedorService.eliminarProveedor(nit);
    }

    public List<ProveedorDTO> listarProveedores() {
        return proveedorService.listarProveedores();
    }

    public boolean activarProveedor(String nit) {
        return proveedorService.activarProveedor(nit);
    }

    public boolean inactivarProveedor(String nit) {
        return proveedorService.inactivarProveedor(nit);
    }


}
