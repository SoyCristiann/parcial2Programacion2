package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;
import co.edu.uniquindio.SOLID.Model.Empleado;

public final class EmpleadoMapper {

    public static EmpleadoDTO toDTO(Empleado empleado) {
        if (empleado == null) {
            return null;
        }
        String rolString = empleado.getRol().name();

        EmpleadoDTO.Rol rolDTO = EmpleadoDTO.Rol.valueOf(rolString);

        return new EmpleadoDTO(
                empleado.getId(),
                empleado.getNombre(),
                rolDTO,
                empleado.isActivo()
        );

    }



    public static Empleado toEntity(EmpleadoDTO empleadoDTO) {
        if(empleadoDTO == null){
            return null;
        }
        String rolString = empleadoDTO.getRol().name();

        Empleado.Rol rolEntity = Empleado.Rol.valueOf(rolString);

        return new Empleado(
                empleadoDTO.getId(),
                empleadoDTO.getNombre(),
                rolEntity,
                empleadoDTO.isActivo()
        );
    }

    public static void updateEntityFromDTO(Empleado entity, EmpleadoDTO dto) {
        if (entity == null || dto == null) return;
        String rolString = dto.getRol().name();
        Empleado.Rol rolEntity = Empleado.Rol.valueOf(rolString);
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());

        entity.setRol(rolEntity);

        entity.setActivo(dto.isActivo());
    }




}

