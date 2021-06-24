package es.taw.eventosgospring.dto;

public class EntradaDTO {
    private Integer id;
    private EventoDTO eventoByIdEvento;
    private UsuarioEventoDTO usuarioEventoByIdUsuario;
    private EntradaAforoDTO entradaAforoById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EventoDTO getEventoByIdEvento() {
        return eventoByIdEvento;
    }

    public void setEventoByIdEvento(EventoDTO eventoByIdEvento) {
        this.eventoByIdEvento = eventoByIdEvento;
    }

    public UsuarioEventoDTO getUsuarioEventoByIdUsuario() {
        return usuarioEventoByIdUsuario;
    }

    public void setUsuarioEventoByIdUsuario(UsuarioEventoDTO usuarioEventoByIdUsuario) {
        this.usuarioEventoByIdUsuario = usuarioEventoByIdUsuario;
    }

    public EntradaAforoDTO getEntradaAforoById() {
        return entradaAforoById;
    }

    public void setEntradaAforoById(EntradaAforoDTO entradaAforoById) {
        this.entradaAforoById = entradaAforoById;
    }
}
