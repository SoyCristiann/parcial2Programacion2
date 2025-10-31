package co.edu.uniquindio.SOLID.Model.DTO;

import co.edu.uniquindio.SOLID.Model.MovimientoInventario;
import co.edu.uniquindio.SOLID.Model.Producto;

import java.time.LocalDateTime;

public class MovimientoInventarioDTO {

    public enum Tipo { ENTRADA }

    private String id;
    private MovimientoInventario.Tipo tipo;
    private LocalDateTime fecha;
    private Producto producto;
    private int cantidad;
    private String referencia;

    public MovimientoInventarioDTO(String id, MovimientoInventario.Tipo tipo, Producto producto, int cantidad, String referencia) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = LocalDateTime.now();
        this.producto = producto;
        this.cantidad = cantidad;
        this.referencia = referencia;
    }

    public String getId() { return id; }
    public MovimientoInventario.Tipo getTipo() { return tipo; }
    public LocalDateTime getFecha() { return fecha; }
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public String getReferencia() { return referencia; }
}
