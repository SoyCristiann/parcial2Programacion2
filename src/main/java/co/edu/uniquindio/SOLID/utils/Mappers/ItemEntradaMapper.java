package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.DTO.ItemPedidoDTO;
import co.edu.uniquindio.SOLID.Model.ItemPedido;

public class ItemPedidoMapper {

    public static ItemPedido toEntity(ItemPedidoDTO itemPedidoDTO){
        if(itemPedidoDTO == null) return null;
        return new ItemPedido(cliente.getCedula(), cliente.getNombre(), cliente.getCorreo(), cliente.getTelefono()); //crea un nuevo objeto de tipo ClienteDTO con los datos extraídos del objeto Cliente.
    }
}
