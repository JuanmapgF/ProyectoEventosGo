package es.taw.eventosgospring.entity;

import es.taw.eventosgospring.dto.MensajeDTO;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
public class Mensaje {
    private Integer id;
    private Date fecha;
    private Time hora;
    private String texto;
    private Integer visto;
    private Conversacion conversacionByIdConversacion;
    private Usuario usuarioByIdUsuario;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FECHA", nullable = false)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "HORA", nullable = false)
    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    @Basic
    @Column(name = "TEXTO", nullable = false, length = -1)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Basic
    @Column(name = "VISTO", nullable = false)
    public Integer getVisto() {
        return visto;
    }

    public void setVisto(Integer visto) {
        this.visto = visto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensaje mensaje = (Mensaje) o;
        return Objects.equals(id, mensaje.id) && Objects.equals(fecha, mensaje.fecha) && Objects.equals(hora, mensaje.hora) && Objects.equals(texto, mensaje.texto) && Objects.equals(visto, mensaje.visto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, hora, texto, visto);
    }

    @ManyToOne
    @JoinColumn(name = "ID_CONVERSACION", referencedColumnName = "ID", nullable = false)
    public Conversacion getConversacionByIdConversacion() {
        return conversacionByIdConversacion;
    }

    public void setConversacionByIdConversacion(Conversacion conversacionByIdConversacion) {
        this.conversacionByIdConversacion = conversacionByIdConversacion;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Usuario usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    @Transient
    public MensajeDTO getDTO(){
        MensajeDTO dto = new MensajeDTO();
        dto.setId(this.id);
        dto.setFecha(this.fecha);
        dto.setHora(this.hora);
        dto.setTexto(this.texto);
        dto.setVisto(this.visto);
        dto.setConversacionByIdConversacion(this.conversacionByIdConversacion.getId());
        dto.setUsuarioByIdUsuario(this.usuarioByIdUsuario.getId());

        return dto;
    }
}
