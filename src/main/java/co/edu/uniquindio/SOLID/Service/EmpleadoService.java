package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;
import co.edu.uniquindio.SOLID.Model.Empleado;
import co.edu.uniquindio.SOLID.Model.Minimercado;
import co.edu.uniquindio.SOLID.utils.Mappers.EmpleadoMapper;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoService {

    private final Minimercado minimercado;

    public EmpleadoService() {
        this.minimercado = Minimercado.getInstancia();
    }

    // Crear empleado
    public boolean crearEmpleado(EmpleadoDTO empleadoDTO) {
        if (buscarEmpleado(empleadoDTO.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con ese ID");
        }
        Empleado empleado = EmpleadoMapper.toEntity(empleadoDTO);
        minimercado.addEmpleado(empleado);
        System.out.println("Lista de emepleados: " + minimercado.getEmpleados());
        return true;
    }

    // Buscar empleado (uso interno)
    private EmpleadoDTO buscarEmpleado(String idEmpleado) {
        for (Empleado e : minimercado.getEmpleados()) {
            if (e.getId().equals(idEmpleado)){
                return EmpleadoMapper.toDTO(e);
            }
        }
        return null;
    }

    // Actualizar empleado
    public boolean actualizarEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado empleado = buscarEmpleadoEntity(empleadoDTO.getId());
        System.out.println("Empleado para actualizar: " + empleado);
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + empleadoDTO.getId());
        }
        EmpleadoMapper.updateEntityFromDTO(empleado, empleadoDTO);
        return true;
    }

    // Eliminar empleado
    public boolean eliminarEmpleado(String idEmpleado) {
        Empleado empleado = buscarEmpleadoEntity(idEmpleado);
        System.out.println("Empleado para eliminar: " + empleado);
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + idEmpleado);
        }
        minimercado.getEmpleados().remove(empleado);
        return true;
    }

    // Listar empleados
    public List<EmpleadoDTO> listarEmpleados() {
        List<EmpleadoDTO> listaDTO = new ArrayList<>();
        for (Empleado e : minimercado.getEmpleados()) {
            listaDTO.add(EmpleadoMapper.toDTO(e));
        }
        return listaDTO;
    }

    // Activar empleado
    public boolean activarEmpleado(String id) {
        Empleado empleado = buscarEmpleadoEntity(id);
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + id);
        }
        empleado.activar();
        return true;
    }

    // Inactivar empleado
    public boolean inactivarEmpleado(String id) {
        Empleado empleado = buscarEmpleadoEntity(id);
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado: " + id);
        }
        empleado.inactivar();
        return true;
    }

    public Empleado buscarEmpleadoEntity(String id){
        for(Empleado e: minimercado.getEmpleados()){
            if(e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }
}
