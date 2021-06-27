package es.taw.eventosgospring.dto;

import java.util.List;

public class ConversacionDTO {
    private Integer id;
    private String asunto;
    private Integer usuarioByIdTeleoperador;
    private Integer usuarioByIdUsuario;
    private List<MensajeDTO> mensajesById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Integer getUsuarioByIdTeleoperador() {
        return usuarioByIdTeleoperador;
    }

    public void setUsuarioByIdTeleoperador(Integer usuarioByIdTeleoperador) {
        this.usuarioByIdTeleoperador = usuarioByIdTeleoperador;
    }

    public Integer getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Integer usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    public List<MensajeDTO> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<MensajeDTO> mensajesById) {
        this.mensajesById = mensajesById;
    }
}
