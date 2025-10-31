package co.edu.uniquindio.SOLID.Model.DTO;

public class MovimientoInventarioDTO {

    private String idMovimiento;
    private String tipoMovimiento;
    private String skuProductoMovimiento;
    private String cantidadMovimiento;
    private String referenciaMovimiento;

    public MovimientoInventarioDTO() {
    }

    public MovimientoInventarioDTO(String id, String tipo, String sku, String cantidad, String referencia) {
        this.idMovimiento= id;
        this.tipoMovimiento= tipo;
        this.skuProductoMovimiento= sku;
        this.cantidadMovimiento= cantidad;
        this.referenciaMovimiento= referencia;
    }

    //Opcionales get - set

    public String getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(String id) {
        this.idMovimiento = id;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getSkuProductoMovimiento() {
        return skuProductoMovimiento;
    }

    public void setSkuProductoMovimiento(String skuProductoMovimiento) {
        this.skuProductoMovimiento = skuProductoMovimiento;
    }

    public String getCantidadMovimiento(){
        return cantidadMovimiento;
    }

    public void setCantidadMovimiento(String cantidad) {
        this.cantidadMovimiento = cantidad;
    }

    public String getReferenciaMovimiento(){
        return referenciaMovimiento;
    }

    public void setReferenciaMovimiento(String referencia) {
        this.referenciaMovimiento = referencia;
    }

    @Override
    public String toString() {
        return "MovimientoInventarioDTO{" +
                "idMovimiento='" + idMovimiento + '\'' +
                ", tipoMovimiento='" + tipoMovimiento + '\'' +
                ", skuProductoMovimiento='" + skuProductoMovimiento + '\'' +
                ", cantidadMovimiento='" + cantidadMovimiento + '\'' +
                ", referenciaMovimiento='" + referenciaMovimiento + '\'' +
                '}';
    }
}
