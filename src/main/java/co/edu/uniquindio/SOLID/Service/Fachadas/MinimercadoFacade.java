package co.edu.uniquindio.SOLID.Service.Fachadas;

import co.edu.uniquindio.SOLID.Model.DTO.*;
import co.edu.uniquindio.SOLID.Service.*;

import java.util.List;

/**
 * Facade que actúa como punto de entrada
 * Delega toda la lógica a los servicios correspondientes
 */
public class MinimercadoFacade {
    
    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final PedidoService pedidoService;
    
    public MinimercadoFacade() {
        this.clienteService = new ClienteService();
        this.productoService = new ProductoService();
        CatalogoProductosService catalogoProductosService = new CatalogoProductosService();
        this.pedidoService = new PedidoService(catalogoProductosService);
    }
    
    // ========== DELEGACIÓN SIMPLE A SERVICIOS ==========
    
    // Clientes
    public List<ClienteDTO> obtenerTodosLosClientes() {
        return clienteService.obtenerTodosLosClientes();
    }
    
    public ClienteDTO buscarClientePorCedula(String cedula) {
        return clienteService.buscarClientePorCedula(cedula);
    }
    
    public boolean agregarCliente(ClienteDTO clienteDTO) {
        return clienteService.agregarCliente(clienteDTO);
    }
    
    public boolean actualizarCliente(ClienteDTO clienteDTO) {
        return clienteService.actualizarCliente(clienteDTO);
    }
    
    public boolean eliminarCliente(String cedula) {
        return clienteService.eliminarCliente(cedula);
    }
    
    public boolean existeCliente(String cedula) {
        return clienteService.existeCliente(cedula);
    }
    

    
    // Pedidos
    public ResumenPedidoDTO procesarPedido(PedidoDTO pedidoDTO) {
        return pedidoService.procesarPedido(pedidoDTO);
    }
    
    public double calcularSubtotal(List<ItemPedidoDTO> items) {
        return pedidoService.calcularSubtotal(items);
    }
    
    public double calcularCostoEnvio(String tipoEnvio) {
        return pedidoService.calcularCostoEnvio(tipoEnvio);
    }
    
    public double calcularTotal(double subtotal, double costoEnvio) {
        return pedidoService.calcularTotal(subtotal, costoEnvio);
    }
}