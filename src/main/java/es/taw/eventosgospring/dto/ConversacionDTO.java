package es.taw.eventosgospring.dto;

import java.util.List;

public class ConversacionDTO {
    private Integer id;
    private String asunto;
    private UsuarioDTO usuarioByIdTeleoperador;
    private UsuarioDTO usuarioByIdUsuario;
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

    public UsuarioDTO getUsuarioByIdTeleoperador() {
        return usuarioByIdTeleoperador;
    }

    public void setUsuarioByIdTeleoperador(UsuarioDTO usuarioByIdTeleoperador) {
        this.usuarioByIdTeleoperador = usuarioByIdTeleoperador;
    }

    public UsuarioDTO getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(UsuarioDTO usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    public List<MensajeDTO> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<MensajeDTO> mensajesById) {
        this.mensajesById = mensajesById;
    }
}
