package es.taw.eventosgospring.entity;

import es.taw.eventosgospring.dto.ConversacionDTO;
import es.taw.eventosgospring.dto.EstudioDTO;
import es.taw.eventosgospring.dto.MensajeDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario {
    private Integer id;
    private String correo;
    private String contrasena;
    private String nombre;
    private Integer rol;
    private List<Conversacion> conversacionsById;
    private List<Conversacion> conversacionsById_0;
    private List<Estudio> estudiosById;
    private List<Evento> eventosById;
    private List<Mensaje> mensajesById;
    private UsuarioEvento usuarioEventoById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CORREO", nullable = false, length = 50)
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Basic
    @Column(name = "CONTRASENA", nullable = false, length = 30)
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "ROL", nullable = false)
    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(correo, usuario.correo) && Objects.equals(contrasena, usuario.contrasena) && Objects.equals(nombre, usuario.nombre) && Objects.equals(rol, usuario.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, correo, contrasena, nombre, rol);
    }

    @OneToMany(mappedBy = "usuarioByIdTeleoperador")
    public List<Conversacion> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(List<Conversacion> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    @OneToMany(mappedBy = "usuarioByIdUsuario")
    public List<Conversacion> getConversacionsById_0() {
        return conversacionsById_0;
    }

    public void setConversacionsById_0(List<Conversacion> conversacionsById_0) {
        this.conversacionsById_0 = conversacionsById_0;
    }

    @OneToMany(mappedBy = "usuarioByIdAnalista")
    public List<Estudio> getEstudiosById() {
        return estudiosById;
    }

    public void setEstudiosById(List<Estudio> estudiosById) {
        this.estudiosById = estudiosById;
    }

    @OneToMany(mappedBy = "usuarioByIdCreador")
    public List<Evento> getEventosById() {
        return eventosById;
    }

    public void setEventosById(List<Evento> eventosById) {
        this.eventosById = eventosById;
    }

    @OneToMany(mappedBy = "usuarioByIdUsuario")
    public List<Mensaje> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<Mensaje> mensajesById) {
        this.mensajesById = mensajesById;
    }

    @OneToOne(mappedBy = "usuarioById")
    public UsuarioEvento getUsuarioEventoById() {
        return usuarioEventoById;
    }

    public void setUsuarioEventoById(UsuarioEvento usuarioEventoById) {
        this.usuarioEventoById = usuarioEventoById;
    }

    @Transient
    public UsuarioDTO getDTO(){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(this.id);
        dto.setCorreo(correo);
        dto.setContrasena(contrasena);
        dto.setRol(rol);
        dto.setNombre(this.nombre);
        List<Integer> conversacionList = new ArrayList<>();
        for(Conversacion c: this.conversacionsById) {
            conversacionList.add(c.getId());
        }
        dto.setConversacionsById(conversacionList);
        List<Integer> conversacionList1 = new ArrayList<>();
        for(Conversacion c: this.conversacionsById_0) {
            conversacionList1.add(c.getId());
        }
        dto.setConversacionsById_0(conversacionList1);
        List<Integer> estudioDTOS = new ArrayList<>();
        for(Estudio e : this.estudiosById) {
            estudioDTOS.add(e.getId());
        }
        dto.setEstudiosById(estudioDTOS);
        List<Integer> mensajeDTOS = new ArrayList<>();
        for(Mensaje m : this.mensajesById) {
            mensajeDTOS.add(m.getId());
        }
        dto.setMensajesById(mensajeDTOS);
        if(dto.getRol () == 4){
            dto.setUsuarioEventoById(this.usuarioEventoById.getId());
        }

        return dto;
    }
}
