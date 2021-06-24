package es.taw.eventosgospring.dto;

public class EventoAforoDTO {
    private Integer id;
    private Integer filas;
    private Integer asientos;
    private EventoDTO eventoById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFilas() {
        return filas;
    }

    public void setFilas(Integer filas) {
        this.filas = filas;
    }

    public Integer getAsientos() {
        return asientos;
    }

    public void setAsientos(Integer asientos) {
        this.asientos = asientos;
    }

    public EventoDTO getEventoById() {
        return eventoById;
    }

    public void setEventoById(EventoDTO eventoById) {
        this.eventoById = eventoById;
    }
}
