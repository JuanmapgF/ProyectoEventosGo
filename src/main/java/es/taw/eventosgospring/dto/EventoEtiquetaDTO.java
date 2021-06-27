package es.taw.eventosgospring.dto;

public class EventoEtiquetaDTO {
    private Integer id;
    private Integer eventoByIdEvento;
    private Integer etiquetaByIdEtiqueta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventoByIdEvento() {
        return eventoByIdEvento;
    }

    public void setEventoByIdEvento(Integer eventoByIdEvento) {
        this.eventoByIdEvento = eventoByIdEvento;
    }

    public Integer getEtiquetaByIdEtiqueta() {
        return etiquetaByIdEtiqueta;
    }

    public void setEtiquetaByIdEtiqueta(Integer etiquetaByIdEtiqueta) {
        this.etiquetaByIdEtiqueta = etiquetaByIdEtiqueta;
    }
}
