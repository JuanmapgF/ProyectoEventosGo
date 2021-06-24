package es.taw.eventosgospring.dto;

import java.util.List;

public class UsuarioDTO {
    private Integer id;
    private String correo;
    private String contrasena;
    private String nombre;
    private Integer rol;
    private List<ConversacionDTO> conversacionsById;
    private List<ConversacionDTO> conversacionsById_0;
    private List<EstudioDTO> estudiosById;
    private List<EventoDTO> eventosById;
    private List<MensajeDTO> mensajesById;
    private UsuarioEventoDTO usuarioEventoById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public List<ConversacionDTO> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(List<ConversacionDTO> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    public List<ConversacionDTO> getConversacionsById_0() {
        return conversacionsById_0;
    }

    public void setConversacionsById_0(List<ConversacionDTO> conversacionsById_0) {
        this.conversacionsById_0 = conversacionsById_0;
    }

    public List<EstudioDTO> getEstudiosById() {
        return estudiosById;
    }

    public void setEstudiosById(List<EstudioDTO> estudiosById) {
        this.estudiosById = estudiosById;
    }

    public List<EventoDTO> getEventosById() {
        return eventosById;
    }

    public void setEventosById(List<EventoDTO> eventosById) {
        this.eventosById = eventosById;
    }

    public List<MensajeDTO> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<MensajeDTO> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public UsuarioEventoDTO getUsuarioEventoById() {
        return usuarioEventoById;
    }

    public void setUsuarioEventoById(UsuarioEventoDTO usuarioEventoById) {
        this.usuarioEventoById = usuarioEventoById;
    }
}
