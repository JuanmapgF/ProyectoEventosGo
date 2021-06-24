package es.taw.eventosgospring.dto;

import java.util.List;

public class EtiquetaDTO {
    private Integer id;
    private String nombre;
    private List<EventoEtiquetaDTO> eventoEtiquetasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<EventoEtiquetaDTO> getEventoEtiquetasById() {
        return eventoEtiquetasById;
    }

    public void setEventoEtiquetasById(List<EventoEtiquetaDTO> eventoEtiquetasById) {
        this.eventoEtiquetasById = eventoEtiquetasById;
    }
}
