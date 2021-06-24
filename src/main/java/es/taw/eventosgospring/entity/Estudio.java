package es.taw.eventosgospring.entity;

import es.taw.eventosgospring.dto.EstudioDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Estudio {
    private Integer id;
    private String titulo;
    private String resultado;
    private Usuario usuarioByIdAnalista;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TITULO", nullable = false, length = 100)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "RESULTADO", nullable = false, length = -1)
    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudio estudio = (Estudio) o;
        return Objects.equals(id, estudio.id) && Objects.equals(titulo, estudio.titulo) && Objects.equals(resultado, estudio.resultado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, resultado);
    }

    @ManyToOne
    @JoinColumn(name = "ID_ANALISTA", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByIdAnalista() {
        return usuarioByIdAnalista;
    }

    public void setUsuarioByIdAnalista(Usuario usuarioByIdAnalista) {
        this.usuarioByIdAnalista = usuarioByIdAnalista;
    }

    @Transient
    public EstudioDTO getDTO() {
        EstudioDTO dto = new EstudioDTO();
        dto.setId(this.id);
        dto.setUsuarioByIdAnalista(this.usuarioByIdAnalista.getDTO());
        dto.setTitulo(this.titulo);
        dto.setResultado(this.resultado);

        return dto;
    }
}
