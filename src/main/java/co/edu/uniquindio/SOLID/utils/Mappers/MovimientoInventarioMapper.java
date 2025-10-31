package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.DTO.MovimientoInventarioDTO;
import co.edu.uniquindio.SOLID.Model.MovimientoInventario;
public class MovimientoInventarioMapper {

    public static MovimientoInventarioDTO toDTO(MovimientoInventario movimientoInventario){
        if(movimientoInventario == null) return null;

        return new MovimientoInventarioDTO(
                movimientoInventario.getId(),
                movimientoInventario.getTipo(),
                movimientoInventario.getProducto(),
                movimientoInventario.getCantidad(),
                movimientoInventario.getReferencia()
        );
    }
}