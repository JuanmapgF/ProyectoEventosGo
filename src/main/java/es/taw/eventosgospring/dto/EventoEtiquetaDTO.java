package es.taw.eventosgospring.dto;

public class EventoEtiquetaDTO {
    private Integer id;
    private EventoDTO eventoByIdEvento;
    private EtiquetaDTO etiquetaByIdEtiqueta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EventoDTO getEventoByIdEvento() {
        return eventoByIdEvento;
    }

    public void setEventoByIdEvento(EventoDTO eventoByIdEvento) {
        this.eventoByIdEvento = eventoByIdEvento;
    }

    public EtiquetaDTO getEtiquetaByIdEtiqueta() {
        return etiquetaByIdEtiqueta;
    }

    public void setEtiquetaByIdEtiqueta(EtiquetaDTO etiquetaByIdEtiqueta) {
        this.etiquetaByIdEtiqueta = etiquetaByIdEtiqueta;
    }
}
