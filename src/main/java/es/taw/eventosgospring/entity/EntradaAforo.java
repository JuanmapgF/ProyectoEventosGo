package es.taw.eventosgospring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ENTRADA_AFORO", schema = "EVENTOSGO", catalog = "")
public class EntradaAforo {
    private Integer id;
    private Integer fila;
    private Integer asiento;
    private Entrada entradaById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FILA", nullable = false)
    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    @Basic
    @Column(name = "ASIENTO", nullable = false)
    public Integer getAsiento() {
        return asiento;
    }

    public void setAsiento(Integer asiento) {
        this.asiento = asiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntradaAforo that = (EntradaAforo) o;
        return Objects.equals(id, that.id) && Objects.equals(fila, that.fila) && Objects.equals(asiento, that.asiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fila, asiento);
    }

    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "ID", nullable = false)
    public Entrada getEntradaById() {
        return entradaById;
    }

    public void setEntradaById(Entrada entradaById) {
        this.entradaById = entradaById;
    }
}
