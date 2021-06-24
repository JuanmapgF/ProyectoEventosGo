package es.taw.eventosgospring.dto;

import java.util.Date;
import java.util.List;

public class EventoDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private Date fechaEvento;
    private Date fechaFinReservas;
    private Double coste;
    private Integer maximoEntradasUsuario;
    private Integer aforo;
    private List<EntradaDTO> entradasById;
    private UsuarioDTO usuarioByIdCreador;
    private EventoAforoDTO eventoAforoById;
    private List<EventoEtiquetaDTO> eventoEtiquetasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getFechaFinReservas() {
        return fechaFinReservas;
    }

    public void setFechaFinReservas(Date fechaFinReservas) {
        this.fechaFinReservas = fechaFinReservas;
    }

    public Double getCoste() {
        return coste;
    }

    public void setCoste(Double coste) {
        this.coste = coste;
    }

    public Integer getMaximoEntradasUsuario() {
        return maximoEntradasUsuario;
    }

    public void setMaximoEntradasUsuario(Integer maximoEntradasUsuario) {
        this.maximoEntradasUsuario = maximoEntradasUsuario;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public List<EntradaDTO> getEntradasById() {
        return entradasById;
    }

    public void setEntradasById(List<EntradaDTO> entradasById) {
        this.entradasById = entradasById;
    }

    public UsuarioDTO getUsuarioByIdCreador() {
        return usuarioByIdCreador;
    }

    public void setUsuarioByIdCreador(UsuarioDTO usuarioByIdCreador) {
        this.usuarioByIdCreador = usuarioByIdCreador;
    }

    public EventoAforoDTO getEventoAforoById() {
        return eventoAforoById;
    }

    public void setEventoAforoById(EventoAforoDTO eventoAforoById) {
        this.eventoAforoById = eventoAforoById;
    }

    public List<EventoEtiquetaDTO> getEventoEtiquetasById() {
        return eventoEtiquetasById;
    }

    public void setEventoEtiquetasById(List<EventoEtiquetaDTO> eventoEtiquetasById) {
        this.eventoEtiquetasById = eventoEtiquetasById;
    }
}
