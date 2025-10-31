package co.edu.uniquindio.SOLID.Model.DTO;

import co.edu.uniquindio.SOLID.Model.ItemEntrada;
import co.edu.uniquindio.SOLID.Model.MovimientoInventario;
import co.edu.uniquindio.SOLID.Model.Proveedor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntradaInventarioDTO {
    private String id;
    private Proveedor proveedor;
    private LocalDateTime fecha;
    private List<ItemEntradaDTO> items;
    private String nit;

    public EntradaInventarioDTO(String id, Proveedor proveedor) {
        this.id = id;
        this.proveedor = proveedor;
        this.fecha = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.nit = proveedor.getNit();
    }

    public String getIdEntrada() {
        return id;
    }

    public void setIdEntrada(String idEntrada) {
        this.id = idEntrada;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getNitProveedor() {
        return nit;
    }

    public void setNitProveedor(Proveedor proveedor) {
        this.nit = proveedor.getNit();
    }

    public List<ItemEntradaDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemEntradaDTO> items) {
        this.items = items;
    }

}
