package es.taw.eventosgospring.dto;

public class EntradaAforoDTO {
    private Integer id;
    private Integer fila;
    private Integer asiento;
    private EntradaDTO entradaById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public Integer getAsiento() {
        return asiento;
    }

    public void setAsiento(Integer asiento) {
        this.asiento = asiento;
    }

    public EntradaDTO getEntradaById() {
        return entradaById;
    }

    public void setEntradaById(EntradaDTO entradaById) {
        this.entradaById = entradaById;
    }
}
