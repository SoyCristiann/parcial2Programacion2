package co.edu.uniquindio.SOLID.Model.DTO;

import co.edu.uniquindio.SOLID.Model.Empleado;

public class EmpleadoDTO {


    private String id;
    private String nombre;
    private Empleado.Rol rol;
    private boolean activo;


    public EmpleadoDTO(String id, String nombre, Empleado.Rol rol, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.activo = true;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Empleado.Rol getRol() {
        return rol;
    }

    public void setRol(Empleado.Rol rol) {
        this.rol = rol;
    }


    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    @Override
    public String toString() {
        return "EmpleadoDTO{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", rol=" + rol +
                ", activo=" + activo +
                '}';
    }

}
