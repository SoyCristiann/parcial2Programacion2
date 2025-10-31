package co.edu.uniquindio.SOLID.Service.Fachadas;

import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;
import co.edu.uniquindio.SOLID.Model.Empleado;
import co.edu.uniquindio.SOLID.Service.*;

import java.util.List;

public class EmpresaAdminFacade {
    private final EmpleadoService empleadoService;

    public EmpresaAdminFacade() {
        this.empleadoService = new EmpleadoService();
    }

    //Empleados
    public boolean crearEmpleado (EmpleadoDTO empleadoDTO){
        System.out.println("Empleado a crear: " + empleadoDTO);
        return empleadoService.crearEmpleado(empleadoDTO);
    }

    public boolean actualizarEmpleado (EmpleadoDTO empleadoDTO){
        return empleadoService.actualizarEmpleado(empleadoDTO);
    }

    public boolean eliminarEmpleado (String id){
        return empleadoService.eliminarEmpleado(id);
    }

    public List<EmpleadoDTO> listarEmpleados(){
        return  empleadoService.listarEmpleados();
    }

    public boolean activarEmpleado (String id){
        return empleadoService.activarEmpleado(id);
    }

    public boolean inactivarEmpleado(String id){
        return empleadoService.inactivarEmpleado(id);
    }
}
