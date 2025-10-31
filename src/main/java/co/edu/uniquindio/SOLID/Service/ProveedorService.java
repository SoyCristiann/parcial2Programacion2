package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Model.Proveedor;
import co.edu.uniquindio.SOLID.utils.Mappers.ProveedorMapper;

import java.util.ArrayList;
import java.util.List;

public class ProveedorService {

    private final List<Proveedor> proveedores = new ArrayList<>();


    public ProveedorDTO crearProveedor(ProveedorDTO dto) {
        if (buscarProveedor(dto.getNit()) != null) {
            throw new IllegalArgumentException("Ya existe un proveedor con ese NIT");
        }

        if (!esEmailValido(dto.getEmail())) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido");
        }

        Proveedor proveedor = ProveedorMapper.toEntity(dto);
        proveedor.setActivo(true);
        proveedores.add(proveedor);

        return ProveedorMapper.toDTO(proveedor);
    }


    private Proveedor buscarProveedor(String nit) {
        for (Proveedor p : proveedores) {
            if (p.getNit().equalsIgnoreCase(nit)) {
                return p;
            }
        }
        return null;
    }


    public ProveedorDTO actualizarProveedor(ProveedorDTO dto) {
        Proveedor proveedor = buscarProveedor(dto.getNit());
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + dto.getNit());
        }

        if (!esEmailValido(dto.getEmail())) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido");
        }

        ProveedorMapper.updateEntityFromDTO(proveedor, dto);
        return ProveedorMapper.toDTO(proveedor);
    }


    public void eliminarProveedor(String nit) {
        Proveedor proveedor = buscarProveedor(nit);
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + nit);
        }
        proveedores.remove(proveedor);
    }


    public List<ProveedorDTO> listarProveedores() {
        List<ProveedorDTO> listaDTO = new ArrayList<>();
        for (Proveedor p : proveedores) {
            listaDTO.add(ProveedorMapper.toDTO(p));
        }
        return listaDTO;
    }


     // Activa un proveedor

    public void activarProveedor(String nit) {
        Proveedor proveedor = buscarProveedor(nit);
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + nit);
        }
        proveedor.activar();
    }


     // Inactiva un proveedor

    public void inactivarProveedor(String nit) {
        Proveedor proveedor = buscarProveedor(nit);
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + nit);
        }
        proveedor.inactivar();
    }


    private boolean esEmailValido(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
