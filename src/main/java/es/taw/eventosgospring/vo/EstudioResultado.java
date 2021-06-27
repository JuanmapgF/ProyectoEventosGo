package es.taw.eventosgospring.vo;

import javax.persistence.criteria.CriteriaBuilder;

public class EstudioResultado {

    private String titulo;
    private Integer anio;
    private Integer edad_min;
    private Integer edad_max;
    private Integer masculino;
    private Integer femenino;
    private Integer otro;
    private String ciudad;
    private Integer id;

    public EstudioResultado() {
        this.titulo = "";
        this.anio = 2020;
        this.edad_min = 0;
        this.edad_max = 100;
        this.masculino = -1;
        this.femenino = -1;
        this.otro = -1;
        this.ciudad = "";
        this.id = -1;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEdad_min() {
        return edad_min;
    }

    public void setEdad_min(Integer edad_min) {
        this.edad_min = edad_min;
    }

    public Integer getEdad_max() {
        return edad_max;
    }

    public void setEdad_max(Integer edad_max) {
        this.edad_max = edad_max;
    }

    public Integer getMasculino() {
        return masculino;
    }

    public Integer getFemenino() {
        return femenino;
    }

    public void setFemenino(Integer femenino) {
        this.femenino = femenino;
    }

    public Integer getOtro() {
        return otro;
    }

    public void setOtro(Integer otro) {
        this.otro = otro;
    }

    public void setMasculino(Integer masculino) {
        this.masculino = masculino;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
