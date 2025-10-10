package ar.utn.ba.ddsi.gestionDeAlumnos.services;


import ar.utn.ba.ddsi.gestionDeAlumnos.dto.ContactoDTO;
import ar.utn.ba.ddsi.gestionDeAlumnos.dto.HechoDTO;
import ar.utn.ba.ddsi.gestionDeAlumnos.dto.TipoContacto;
import ar.utn.ba.ddsi.gestionDeAlumnos.exceptions.DuplicateLegajoException;
import ar.utn.ba.ddsi.gestionDeAlumnos.exceptions.NotFoundException;
import ar.utn.ba.ddsi.gestionDeAlumnos.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HechosService {
    @Autowired
    private GestionHechosApiService gestionHechosApiService;

    public List<HechoDTO> obtenerTodosLosHechos() {
        return gestionHechosApiService.obtenerTodosLosHechos();
    }

    public Optional<HechoDTO> obtenerHechoPorLegajo(long id) {
        try {
            HechoDTO hecho = gestionHechosApiService.obtenerHechoPorId(id);
            return Optional.of(hecho);
        } catch (NotFoundException e) {
            return Optional.empty();
        }
    }

    public HechoDTO crearHecho(HechoDTO hechoDTO) {
        validarDatosBasicos(hechoDTO);

        return gestionHechosApiService.crearHecho(hechoDTO);
    }

    public HechoDTO actualizarHecho(long id, HechoDTO hechoDTO) {
        // Verificar que el hecho existe
        gestionHechosApiService.obtenerHechoPorId(id);
        validarDatosBasicos(hechoDTO);

       return gestionHechosApiService.actualizarHecho(id, hechoDTO);
    }

    public void eliminarHecho(long id) {
        gestionHechosApiService.obtenerHechoPorId(id); // Verificar que existe
        gestionHechosApiService.eliminarHecho(id);
    }

    private void validarDatosBasicos(HechoDTO hechoDTO) {
        ValidationException validationException = new ValidationException("Errores de validación");
        boolean tieneErrores = false;


        if (hechoDTO.getTitulo() == null || hechoDTO.getTitulo().trim().isEmpty()) {
            validationException.addFieldError("titulo", "El titulo es obligatorio");
            tieneErrores = true;
        }

        if (hechoDTO.getDescripcion() == null || hechoDTO.getDescripcion().trim().isEmpty()) {
            validationException.addFieldError("descripcion", "La descripcion es obligatoria");
            tieneErrores = true;
        }

        if (hechoDTO.getLatitud() == null) {
            validationException.addFieldError("latitud", "La latitud es obligatoria");
            tieneErrores = true;
        }

        if (hechoDTO.getLongitud() == null) {
            validationException.addFieldError("longitud", "La longitud es obligatoria");
            tieneErrores = true;
        }

        if (hechoDTO.getFechaAcontecimiento() == null) {
            validationException.addFieldError("fechaAcontecimiento", "La Fecha de Acontecimiento es obligatoria");
            tieneErrores = true;
        }

        if (tieneErrores) {
            throw validationException;
        }
    }


}
