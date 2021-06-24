package es.taw.eventosgospring.entity;

import es.taw.eventosgospring.dto.ConversacionDTO;
import es.taw.eventosgospring.dto.MensajeDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Conversacion {
    private Integer id;
    private String asunto;
    private Usuario usuarioByIdTeleoperador;
    private Usuario usuarioByIdUsuario;
    private List<Mensaje> mensajesById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ASUNTO", nullable = false, length = 100)
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversacion that = (Conversacion) o;
        return Objects.equals(id, that.id) && Objects.equals(asunto, that.asunto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, asunto);
    }

    @ManyToOne
    @JoinColumn(name = "ID_TELEOPERADOR", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByIdTeleoperador() {
        return usuarioByIdTeleoperador;
    }

    public void setUsuarioByIdTeleoperador(Usuario usuarioByIdTeleoperador) {
        this.usuarioByIdTeleoperador = usuarioByIdTeleoperador;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Usuario usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    @OneToMany(mappedBy = "conversacionByIdConversacion")
    public List<Mensaje> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<Mensaje> mensajesById) {
        this.mensajesById = mensajesById;
    }

    @Transient
    public ConversacionDTO getDTO(){
        ConversacionDTO dto = new ConversacionDTO();
        dto.setId(this.id);
        dto.setAsunto(this.asunto);
        dto.setUsuarioByIdTeleoperador(this.usuarioByIdTeleoperador.getDTO());
        dto.setUsuarioByIdUsuario(this.usuarioByIdUsuario.getDTO());
        List<MensajeDTO> mensajeList = new ArrayList<>();
        for (Mensaje m:this.mensajesById) {
            mensajeList.add(m.getDTO());
        }
        dto.setMensajesById(mensajeList);

        return dto;
    }
}
