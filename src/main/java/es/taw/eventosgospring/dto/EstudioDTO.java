package es.taw.eventosgospring.dto;

public class EstudioDTO {
    private Integer id;
    private String titulo;
    private String resultado;
    private UsuarioDTO usuarioByIdAnalista;

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

    public UsuarioDTO getUsuarioByIdAnalista() {
        return usuarioByIdAnalista;
    }

    public void setUsuarioByIdAnalista(UsuarioDTO usuarioByIdAnalista) {
        this.usuarioByIdAnalista = usuarioByIdAnalista;
    }
}
