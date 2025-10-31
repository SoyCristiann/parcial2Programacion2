package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.*;
import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.DTO.MovimientoInventarioDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProductoDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.utils.Mappers.MovimientoInventarioMapper;

import java.util.ArrayList;
import java.util.List;

public class InventarioService {
    // Nota: la confirmación y registro de movimientos se hace en EntradaInventario.confirmar()
    private final Minimercado minimercado = Minimercado.getInstancia();
    private final ProveedorService proveedorService= new ProveedorService();
    private final ProductoService productoService= new ProductoService();

    public boolean registrarEntradaInventario(ProveedorDTO proveedorDTO, ProductoDTO productoDTO, int cantidad) {
        Proveedor proveedor= proveedorService.buscarProveedorEntity(proveedorDTO.getNit());
        Producto producto= productoService.buscarProductoEntity(productoDTO.getSku());

        if (proveedor == null) {
            throw new IllegalArgumentException("Se requiere un proveedor");
        }
        if (producto == null) {
            throw new IllegalArgumentException("Se requiere un producto");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        System.out.println("ENTRADA DE INVENTARIO PARA EL PRODUCTO: " + producto.getNombre());
        System.out.println("Stock actual del producto: " + producto.getStock());
        System.out.println("Cantidad que ingresa: " + cantidad);

        EntradaInventario entrada = new EntradaInventario("ENT-" + System.currentTimeMillis(), proveedor);
        entrada.agregarItem(producto, cantidad);
        entrada.confirmar();

        minimercado.addEntradaInventario(entrada);
        for (MovimientoInventario mov : entrada.getMovimientosGenerados()) {
            minimercado.registrarMovimiento(mov);
        }

        System.out.println("Nuevo stock del producto: " + producto.getStock());

        return true;
    }

    public int consultarStock(String sku) {
        Producto producto = productoService.buscarProductoEntity(sku);
        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado: " + sku);
        }
        return producto.getStock();
    }


    public List<MovimientoInventarioDTO> listarMovimientos() {
        List<MovimientoInventario> movimientosEntidad = minimercado.getMovimientos();
        List<MovimientoInventarioDTO> movimientosDTO = new ArrayList<>();

        for (MovimientoInventario movimiento : movimientosEntidad) {
            MovimientoInventarioDTO dto = MovimientoInventarioMapper.toDTO(movimiento);
            if (dto != null) {
                movimientosDTO.add(dto);
            }
        }

        return movimientosDTO;
    }

}
