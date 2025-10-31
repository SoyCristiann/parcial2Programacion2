package co.edu.uniquindio.SOLID.Service.Fachadas;

import co.edu.uniquindio.SOLID.Model.DTO.ClienteDTO;
import co.edu.uniquindio.SOLID.Model.EntradaInventario;
import co.edu.uniquindio.SOLID.Model.Producto;
import co.edu.uniquindio.SOLID.Model.Proveedor;
import co.edu.uniquindio.SOLID.Service.InventarioService;

import java.util.List;

public class InventarioFacade {
    private final InventarioService inventarioService;


    public InventarioFacade() {
        this.inventarioService = new InventarioService();
    }

    public EntradaInventario registrarEntradaInventario(Proveedor proveedor, Producto producto, int cantidad) {
        return inventarioService.registrarEntradaInventario(proveedor, producto, cantidad);
    }

/*
    listarProveedores(): List<ProveedorDTO>
○ crearProveedor(ProveedorDTO): Respuesta
○ actualizarProveedor(ProveedorDTO): Respuesta
○ eliminarProveedor(String nit): Respuesta
○ activarProveedor(String nit): Respuesta
○ inactivarProveedor(String nit): Respuesta
○ listarProductos(): List<ProductoDTO>
○ registrarEntrada(EntradaInventarioDTO)
○ listarMovimientos(): List<MovimientoInventarioDTO>*/
}
