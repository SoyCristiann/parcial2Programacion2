package co.edu.uniquindio.SOLID.Model.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class EntradaInventarioDTO {
    private String idEntrada;
    private LocalDateTime fecha;
    private String nitProveedor;
    private List<ItemEntradaDTO> items;

    public EntradaInventarioDTO(String idEntrada, LocalDateTime fecha, String nitProveedor, List<ItemEntradaDTO> items) {
        this.idEntrada = idEntrada;
        this.fecha = fecha;
        this.nitProveedor = nitProveedor;
        this.items = items;
    }

    public String getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(String idEntrada) {
        this.idEntrada = idEntrada;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public List<ItemEntradaDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemEntradaDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "EntradaInventarioDTO{" +
                "idEntrada='" + idEntrada + '\'' +
                ", fecha='" + fecha + '\'' +
                ", nitProveedor='" + nitProveedor + '\'' +
                ", items=" + items +
                '}';
    }
}
