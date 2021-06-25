package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.*;
import es.taw.eventosgospring.dto.EntradaDTO;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.UsuarioEventoDTO;
import es.taw.eventosgospring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntradaService {
    private EntradaRepository entradaRepository;
    private UsuarioRepository usuarioRepository;
    private UsuarioEventoRepository usuarioEventoRepository;
    private EntradaAforoRepository entradaAforoRepository;
    private EventoRepository eventoRepository;

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Autowired
    public void setUsuarioEventoRepository(UsuarioEventoRepository usuarioEventoRepository) {
        this.usuarioEventoRepository = usuarioEventoRepository;
    }

    @Autowired
    public void setEntradaAforoRepository(EntradaAforoRepository entradaAforoRepository) {
        this.entradaAforoRepository = entradaAforoRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setEntradaRepository(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    protected List<EntradaDTO> convertirAListaDTO(List<Entrada> entradasDisponibles){
        if(entradasDisponibles!=null){
            List<EntradaDTO> entradasDTO = new ArrayList<>();
            for(Entrada e : entradasDisponibles){
                entradasDTO.add(e.getDTO());
            }
            return entradasDTO;
        } else{
            return null;
        }
    }

    public List<EntradaDTO> findByIdEvento(Integer idEvento){
        List<Entrada> entradasCompradas = this.entradaRepository.findByIdEvento(idEvento);

        return this.convertirAListaDTO(entradasCompradas);
    }

    public void guardarEntrada(EntradaDTO dto) {
        Entrada entrada;
        if(dto.getId() == null){
            entrada = new Entrada();
        } else{
            entrada = this.entradaRepository.findById(dto.getId()).orElse(new Entrada());
        }

        // EntradaAforo aforo = this.entradaAforoRepository.findById(dto.getEntradaAforoById()).orElse(null);
        UsuarioEvento userEvento = this.usuarioEventoRepository.findById(dto.getUsuarioEventoByIdUsuario()).orElse(null);
        Evento evento = this.eventoRepository.findById(dto.getEventoByIdEvento()).orElse(null);

        // entrada.setEntradaAforoById(aforo);
        entrada.setEventoByIdEvento(evento);
        entrada.setUsuarioEventoByIdUsuario(userEvento);

        this.entradaRepository.save(entrada);
    }


    public void borrarEntradas(EventoDTO dto, UsuarioEventoDTO usuarioEvento) {

        Evento evento = this.eventoRepository.findById(dto.getId()).orElse(null);
        List<Entrada> entradasTotales = evento.getEntradasById();
        List<Entrada> entradasUsuario = new ArrayList<>();

        for(Entrada e : entradasTotales){
            if(e.getUsuarioEventoByIdUsuario().getId() == usuarioEvento.getId()){
                entradasUsuario.add(e);
            }
        }

        for(Entrada e : entradasUsuario) {
            Entrada entrada = this.entradaRepository.findById(e.getId()).orElse(null);
            UsuarioEvento user = this.usuarioEventoRepository.findById(e.getUsuarioEventoByIdUsuario().getId()).orElse(null);

            if (entrada != null) {
                this.entradaRepository.delete(entrada);
                user.getEntradasById().remove(entrada);

                this.usuarioEventoRepository.save(user);
            }
        }
    }
}
