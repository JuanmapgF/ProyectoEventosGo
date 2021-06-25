package es.taw.eventosgospring.dto;

import java.util.Date;
import java.util.List;

public class UsuarioEventoDTO {
    private Integer id;
    private String apellidos;
    private String domicilio;
    private String ciudad;
    private Date fechaNacimiento;
    private Integer sexo;
    private List<EntradaDTO> entradasById;
    private Integer usuarioById;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public List<EntradaDTO> getEntradasById() {
        return entradasById;
    }

    public void setEntradasById(List<EntradaDTO> entradasById) {
        this.entradasById = entradasById;
    }

    public Integer getUsuarioById() {
        return usuarioById;
    }

    public void setUsuarioById(Integer usuarioById) {
        this.usuarioById = usuarioById;
    }
}
