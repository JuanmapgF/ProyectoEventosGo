package es.taw.eventosgospring.dto;

import java.util.List;

public class UsuarioDTO {
    private Integer id;
    private String correo;
    private String contrasena;
    private String nombre;
    private Integer rol;
    private List<Integer> conversacionsById;
    private List<Integer> conversacionsById_0;
    private List<Integer> estudiosById;
    private List<Integer> eventosById;
    private List<Integer> mensajesById;
    private Integer usuarioEventoById;

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

    public List<Integer> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(List<Integer> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    public List<Integer> getConversacionsById_0() {
        return conversacionsById_0;
    }

    public void setConversacionsById_0(List<Integer> conversacionsById_0) {
        this.conversacionsById_0 = conversacionsById_0;
    }

    public List<Integer> getEstudiosById() {
        return estudiosById;
    }

    public void setEstudiosById(List<Integer> estudiosById) {
        this.estudiosById = estudiosById;
    }

    public List<Integer> getEventosById() {
        return eventosById;
    }

    public void setEventosById(List<Integer> eventosById) {
        this.eventosById = eventosById;
    }

    public List<Integer> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<Integer> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public Integer getUsuarioEventoById() {
        return usuarioEventoById;
    }

    public void setUsuarioEventoById(Integer usuarioEventoById) {
        this.usuarioEventoById = usuarioEventoById;
    }
}
