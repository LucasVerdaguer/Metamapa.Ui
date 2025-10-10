package ar.utn.ba.ddsi.gestionDeAlumnos.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HechoDTO {
    private Long idHecho;
    private String titulo;
    private String descripcion;
    private String categoria;
    //private TipoFuente fuente;
    private Double latitud;
    private Double longitud;
    private LocalDate fechaAcontecimiento;
//    private List<Etiqueta> etiquetas;
//    private List<HechoConsensoDto> consensos;   // múltiples algoritmos
//    private HechoConsensoDto consensoActivo;
}
