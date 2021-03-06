package es.taw.eventosgospring.dto;

import java.util.Date;

public class MensajeDTO {
    private Integer id;
    private Date fecha;
    private Date hora;
    private String texto;
    private Integer visto;
    private Integer conversacionByIdConversacion;
    private Integer usuarioByIdUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getVisto() {
        return visto;
    }

    public void setVisto(Integer visto) {
        this.visto = visto;
    }

    public Integer getConversacionByIdConversacion() {
        return conversacionByIdConversacion;
    }

    public void setConversacionByIdConversacion(Integer conversacionByIdConversacion) {
        this.conversacionByIdConversacion = conversacionByIdConversacion;
    }

    public Integer getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Integer usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }
}
