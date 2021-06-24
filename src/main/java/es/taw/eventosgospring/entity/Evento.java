package es.taw.eventosgospring.entity;

import es.taw.eventosgospring.dto.EventoDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Evento {
    private Integer id;
    private String titulo;
    private String descripcion;
    private Date fechaEvento;
    private Date fechaFinReservas;
    private Double coste;
    private Integer maximoEntradasUsuario;
    private Integer aforo;
    private List<Entrada> entradasById;
    private Usuario usuarioByIdCreador;
    private EventoAforo eventoAforoById;
    private List<EventoEtiqueta> eventoEtiquetasById;

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
    @Column(name = "DESCRIPCION", nullable = false, length = -1)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "FECHA_EVENTO", nullable = false)
    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    @Basic
    @Column(name = "FECHA_FIN_RESERVAS", nullable = false)
    public Date getFechaFinReservas() {
        return fechaFinReservas;
    }

    public void setFechaFinReservas(Date fechaFinReservas) {
        this.fechaFinReservas = fechaFinReservas;
    }

    @Basic
    @Column(name = "COSTE", nullable = false, precision = 0)
    public Double getCoste() {
        return coste;
    }

    public void setCoste(Double coste) {
        this.coste = coste;
    }

    @Basic
    @Column(name = "MAXIMO_ENTRADAS_USUARIO", nullable = false)
    public Integer getMaximoEntradasUsuario() {
        return maximoEntradasUsuario;
    }

    public void setMaximoEntradasUsuario(Integer maximoEntradasUsuario) {
        this.maximoEntradasUsuario = maximoEntradasUsuario;
    }

    @Basic
    @Column(name = "AFORO", nullable = false)
    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id) && Objects.equals(titulo, evento.titulo) && Objects.equals(descripcion, evento.descripcion) && Objects.equals(fechaEvento, evento.fechaEvento) && Objects.equals(fechaFinReservas, evento.fechaFinReservas) && Objects.equals(coste, evento.coste) && Objects.equals(maximoEntradasUsuario, evento.maximoEntradasUsuario) && Objects.equals(aforo, evento.aforo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descripcion, fechaEvento, fechaFinReservas, coste, maximoEntradasUsuario, aforo);
    }

    @OneToMany(mappedBy = "eventoByIdEvento")
    public List<Entrada> getEntradasById() {
        return entradasById;
    }

    public void setEntradasById(List<Entrada> entradasById) {
        this.entradasById = entradasById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_CREADOR", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByIdCreador() {
        return usuarioByIdCreador;
    }

    public void setUsuarioByIdCreador(Usuario usuarioByIdCreador) {
        this.usuarioByIdCreador = usuarioByIdCreador;
    }

    @OneToOne(mappedBy = "eventoById")
    public EventoAforo getEventoAforoById() {
        return eventoAforoById;
    }

    public void setEventoAforoById(EventoAforo eventoAforoById) {
        this.eventoAforoById = eventoAforoById;
    }

    @OneToMany(mappedBy = "eventoByIdEvento")
    public List<EventoEtiqueta> getEventoEtiquetasById() {
        return eventoEtiquetasById;
    }

    public void setEventoEtiquetasById(List<EventoEtiqueta> eventoEtiquetasById) {
        this.eventoEtiquetasById = eventoEtiquetasById;
    }

    @Transient
    public EventoDTO getDTO(){
        EventoDTO dto = new EventoDTO();
        dto.setId(id);
        dto.setTitulo(titulo);
        dto.setDescripcion(descripcion);
        dto.setAforo(this.aforo);
        dto.setCoste(this.coste);
        dto.setFechaEvento(this.fechaEvento);
        // dto.setEventoAforoById(this.eventoAforoById.getId());
        dto.setFechaFinReservas(this.fechaFinReservas);
        dto.setMaximoEntradasUsuario(this.maximoEntradasUsuario);
        dto.setUsuarioByIdCreador(this.getUsuarioByIdCreador().getId());

        List<Integer> etiquetasList = new ArrayList<>();
        for(EventoEtiqueta e: this.eventoEtiquetasById) {
            etiquetasList.add(e.getId());
        }
        dto.setEventoEtiquetasById(etiquetasList);

        List<Integer> entradasList = new ArrayList<>();
        for(Entrada e: this.entradasById) {
            entradasList.add(e.getId());
        }
        dto.setEntradasById(entradasList);

        return dto;
    }
}
