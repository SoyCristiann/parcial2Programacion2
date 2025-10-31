package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.EntradaInventario;
import co.edu.uniquindio.SOLID.Model.Producto;
import co.edu.uniquindio.SOLID.Model.Proveedor;

public class InventarioService {
    // Nota: la confirmación y registro de movimientos se hace en EntradaInventario.confirmar()

    public EntradaInventario registrarEntradaInventario(Proveedor proveedor, Producto producto, int cantidad) {
        if (proveedor == null) {
            throw new IllegalArgumentException("Se requiere un proveedor");
        }
        if (producto == null) {
            throw new IllegalArgumentException("Se requiere un producto");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        EntradaInventario entrada = new EntradaInventario("ENT-" + System.currentTimeMillis(), proveedor);
        entrada.agregarItem(producto, cantidad);
        entrada.confirmar();
        return entrada;
    }


}
