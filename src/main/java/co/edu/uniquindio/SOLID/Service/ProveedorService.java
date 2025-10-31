package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Model.Minimercado;
import co.edu.uniquindio.SOLID.Model.Proveedor;
import co.edu.uniquindio.SOLID.utils.Mappers.ProveedorMapper;

import java.util.ArrayList;
import java.util.List;

public class ProveedorService {
    private final Minimercado minimercado;

    public ProveedorService() {
        this.minimercado = Minimercado.getInstancia();
    }


    public boolean crearProveedor(ProveedorDTO proveedorDTO) {
        if (buscarProveedorEntity(proveedorDTO.getNit()) != null) {
            throw new IllegalArgumentException("Ya existe un proveedor con ese NIT");
        }
        if (!esEmailValido(proveedorDTO.getEmail())) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido");
        }
        Proveedor proveedor = ProveedorMapper.toEntity(proveedorDTO);
        minimercado.addProveedor(proveedor);
        return true;
    }


    public boolean actualizarProveedor(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = buscarProveedorEntity(proveedorDTO.getNit());
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + proveedorDTO.getNit());
        }

        if (!esEmailValido(proveedorDTO.getEmail())) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido");
        }
        ProveedorMapper.updateEntityFromDTO(proveedor, proveedorDTO);
        return true;
    }


    public boolean eliminarProveedor(String nit) {
        Proveedor proveedor = buscarProveedorEntity(nit);
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + nit);
        }
        minimercado.getProveedores().remove(proveedor);
        return true;
    }


    public List<ProveedorDTO> listarProveedores() {
        List<ProveedorDTO> listaDTO = new ArrayList<>();
        for (Proveedor p : minimercado.getProveedores()) {
            listaDTO.add(ProveedorMapper.toDTO(p));
        }
        return listaDTO;
    }


     // Activa un proveedor
    public boolean activarProveedor(String nit) {
        Proveedor proveedor= buscarProveedorEntity(nit);
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + nit);
        }
        proveedor.activar();
        return true;
    }


     // Inactiva un proveedor

    public boolean inactivarProveedor(String nit) {
        Proveedor proveedor = buscarProveedorEntity(nit);
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + nit);
        }
        proveedor.inactivar();
        return true;
    }


    private boolean esEmailValido(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public Proveedor buscarProveedorEntity(String nit){
        for(Proveedor p: minimercado.getProveedores()){
            if(p.getNit().equals(nit)){
                return p;
            }
        }
        return null;
    }
}
