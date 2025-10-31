package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;
import co.edu.uniquindio.SOLID.Model.Empleado;
import co.edu.uniquindio.SOLID.utils.Mappers.EmpleadoMapper;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoService {

    private final List<Empleado> empleados = new ArrayList<>();

    // Crear empleado
    public EmpleadoDTO crearEmpleado(EmpleadoDTO dto) {
        if (buscarEmpleado(dto.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con ese ID");
        }

        Empleado empleado = EmpleadoMapper.toEntity(dto);
        empleado.setActivo(true);
        empleados.add(empleado);

        return EmpleadoMapper.toDTO(empleado);
    }

    // Buscar empleado (uso interno)
    private Empleado buscarEmpleado(String id) {
        for (Empleado e : empleados) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }

    // Actualizar empleado
    public EmpleadoDTO actualizarEmpleado(EmpleadoDTO dto) {
        Empleado empleado = buscarEmpleado(dto.getId());
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + dto.getId());
        }

        EmpleadoMapper.updateEntityFromDTO(empleado, dto);
        return EmpleadoMapper.toDTO(empleado);
    }

    // Eliminar empleado
    public void eliminarEmpleado(String id) {
        Empleado empleado = buscarEmpleado(id);
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + id);
        }
        empleados.remove(empleado);
    }

    // Listar empleados
    public List<EmpleadoDTO> listarEmpleados() {
        List<EmpleadoDTO> listaDTO = new ArrayList<>();
        for (Empleado e : empleados) {
            listaDTO.add(EmpleadoMapper.toDTO(e));
        }
        return listaDTO;
    }

    // Activar empleado
    public void activarEmpleado(String id) {
        Empleado empleado = buscarEmpleado(id);
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + id);
        }
        empleado.activar();
    }

    // Inactivar empleado
    public void inactivarEmpleado(String id) {
        Empleado empleado = buscarEmpleado(id);
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + id);
        }
        empleado.inactivar();
    }
}
