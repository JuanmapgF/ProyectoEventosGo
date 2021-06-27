package es.taw.eventosgospring.dto;

public class EntradaDTO {
    private Integer id;
    private Integer eventoByIdEvento;
    private Integer usuarioEventoByIdUsuario;
    private Integer entradaAforoById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventoByIdEvento() {
        return eventoByIdEvento;
    }

    public void setEventoByIdEvento(Integer eventoByIdEvento) {
        this.eventoByIdEvento = eventoByIdEvento;
    }

    public Integer getUsuarioEventoByIdUsuario() {
        return usuarioEventoByIdUsuario;
    }

    public void setUsuarioEventoByIdUsuario(Integer usuarioEventoByIdUsuario) {
        this.usuarioEventoByIdUsuario = usuarioEventoByIdUsuario;
    }

    public Integer getEntradaAforoById() {
        return entradaAforoById;
    }

    public void setEntradaAforoById(Integer entradaAforoById) {
        this.entradaAforoById = entradaAforoById;
    }
}
