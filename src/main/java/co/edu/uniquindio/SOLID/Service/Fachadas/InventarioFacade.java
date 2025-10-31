package co.edu.uniquindio.SOLID.Service.Fachadas;

import co.edu.uniquindio.SOLID.Model.DTO.ClienteDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProductoDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Model.EntradaInventario;
import co.edu.uniquindio.SOLID.Model.Producto;
import co.edu.uniquindio.SOLID.Model.Proveedor;
import co.edu.uniquindio.SOLID.Service.InventarioService;
import co.edu.uniquindio.SOLID.Service.ProductoService;
import co.edu.uniquindio.SOLID.Service.ProveedorService;

import java.util.List;

public class InventarioFacade {
    private final InventarioService inventarioService;
    private final ProveedorService proveedorService;
    private final ProductoService productoService;


    public InventarioFacade() {
        this.inventarioService = new InventarioService();
        this.proveedorService = new ProveedorService();
        this.productoService = new ProductoService();
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

    // Productos
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    public ProductoDTO buscarProductoPorSku(String sku) {
        return productoService.buscarProductoPorSku(sku);
    }

    public boolean agregarProducto(ProductoDTO productoDTO) {
        return productoService.agregarProducto(productoDTO);
    }

    public boolean actualizarProducto(ProductoDTO productoDTO) {
        return productoService.actualizarProducto(productoDTO);
    }

    public boolean eliminarProducto(String sku) {
        return productoService.eliminarProducto(sku);
    }

    public boolean existeProducto(String sku) {
        return productoService.existeProducto(sku);
    }

    //Inventario
    public boolean registrarEntradaInventario(ProveedorDTO proveedorDTO, ProductoDTO productoDTO, int cantidad) {
        return inventarioService.registrarEntradaInventario(proveedorDTO, productoDTO, cantidad);
    }

    //consultar Stock
    //listarMovimientos

/*
○ listarProductos(): List<ProductoDTO>
○ registrarEntrada(EntradaInventarioDTO)
○ listarMovimientos(): List<MovimientoInventarioDTO>*/
}
