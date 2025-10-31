package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.EntradaInventario;
import co.edu.uniquindio.SOLID.Model.Proveedor;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EntradaInventarioMapper {
    public static EntradaInventario toEntity(EntradaInventarioDTO entradaInventarioDTO, Proveedor proveedor) {
        if (entradaInventarioDTO == null) return null; //Si entradaInventarioDTO viene null, así lo retorna.
        return new EntradaInventario(entradaInventarioDTO.getIdEntrada(), proveedor);
    }
}
