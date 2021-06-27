package es.taw.eventosgospring.dto;

public class EstudioDTO {
    private Integer id;
    private String titulo;
    private String resultado;
    private Integer usuarioIdAnalista;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Integer getUsuarioIdAnalista() {
        return usuarioIdAnalista;
    }

    public void setUsuarioIdAnalista(Integer usuarioIdAnalista) {
        this.usuarioIdAnalista = usuarioIdAnalista;
    }
}
