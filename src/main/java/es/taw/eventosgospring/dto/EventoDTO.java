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
    private List<Integer> entradasById;
    private Integer usuarioByIdCreador;
    private Integer eventoAforoById;
    private List<Integer> eventoEtiquetasById;

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

    public List<Integer> getEntradasById() {
        return entradasById;
    }

    public void setEntradasById(List<Integer> entradasById) {
        this.entradasById = entradasById;
    }

    public Integer getUsuarioByIdCreador() {
        return usuarioByIdCreador;
    }

    public void setUsuarioByIdCreador(Integer usuarioByIdCreador) {
        this.usuarioByIdCreador = usuarioByIdCreador;
    }

    public Integer getEventoAforoById() {
        return eventoAforoById;
    }

    public void setEventoAforoById(Integer eventoAforoById) {
        this.eventoAforoById = eventoAforoById;
    }

    public List<Integer> getEventoEtiquetasById() {
        return eventoEtiquetasById;
    }

    public void setEventoEtiquetasById(List<Integer> eventoEtiquetasById) {
        this.eventoEtiquetasById = eventoEtiquetasById;
    }


}
