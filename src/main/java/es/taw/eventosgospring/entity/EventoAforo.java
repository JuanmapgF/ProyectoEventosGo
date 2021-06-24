package es.taw.eventosgospring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EVENTO_AFORO", schema = "EVENTOSGO", catalog = "")
public class EventoAforo {
    private Integer id;
    private Integer filas;
    private Integer asientos;
    private Evento eventoById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FILAS", nullable = false)
    public Integer getFilas() {
        return filas;
    }

    public void setFilas(Integer filas) {
        this.filas = filas;
    }

    @Basic
    @Column(name = "ASIENTOS", nullable = false)
    public Integer getAsientos() {
        return asientos;
    }

    public void setAsientos(Integer asientos) {
        this.asientos = asientos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoAforo that = (EventoAforo) o;
        return Objects.equals(id, that.id) && Objects.equals(filas, that.filas) && Objects.equals(asientos, that.asientos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filas, asientos);
    }

    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "ID", nullable = false)
    public Evento getEventoById() {
        return eventoById;
    }

    public void setEventoById(Evento eventoById) {
        this.eventoById = eventoById;
    }
}
