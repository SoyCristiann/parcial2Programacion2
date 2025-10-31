package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.DTO.ItemEntradaDTO;
import co.edu.uniquindio.SOLID.Model.ItemEntrada;

public class ItemEntradaMapper {

    public static ItemEntradaDTO toDTO(ItemEntrada itemEntrada){
        if(itemEntrada == null) return null;
        return new ItemEntradaDTO(itemEntrada.getProducto().getSku(), itemEntrada.getCantidad());
    }
}
