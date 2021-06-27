package es.taw.eventosgospring.entity;

import es.taw.eventosgospring.dto.EventoEtiquetaDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EVENTO_ETIQUETA", schema = "EVENTOSGO", catalog = "")
public class EventoEtiqueta {
    private Integer id;
    private Evento eventoByIdEvento;
    private Etiqueta etiquetaByIdEtiqueta;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoEtiqueta that = (EventoEtiqueta) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID", nullable = false)
    public Evento getEventoByIdEvento() {
        return eventoByIdEvento;
    }

    public void setEventoByIdEvento(Evento eventoByIdEvento) {
        this.eventoByIdEvento = eventoByIdEvento;
    }

    @ManyToOne
    @JoinColumn(name = "ID_ETIQUETA", referencedColumnName = "ID", nullable = false)
    public Etiqueta getEtiquetaByIdEtiqueta() {
        return etiquetaByIdEtiqueta;
    }

    public void setEtiquetaByIdEtiqueta(Etiqueta etiquetaByIdEtiqueta) {
        this.etiquetaByIdEtiqueta = etiquetaByIdEtiqueta;
    }

    @Transient
    public EventoEtiquetaDTO getDTO(){
        EventoEtiquetaDTO dto = new EventoEtiquetaDTO();
        dto.setId(this.getId());
        dto.setEventoByIdEvento(this.getEventoByIdEvento().getId());
        dto.setEtiquetaByIdEtiqueta(this.getEtiquetaByIdEtiqueta().getId());

        return dto;
    }
}
