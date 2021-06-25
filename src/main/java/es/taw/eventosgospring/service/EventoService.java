package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.EventoRepository;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.entity.Evento;
import es.taw.eventosgospring.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventoService {

    private EventoRepository eventoRepository;

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    protected List<EventoDTO> convertirAListaDTO(List<Evento> eventosDisponibles){
        if(eventosDisponibles!=null){
            List<EventoDTO> eventosDTO = new ArrayList<>();
            for(Evento e : eventosDisponibles){
                eventosDTO.add(e.getDTO());
            }
            return eventosDTO;
        } else{
            return null;
        }
    }

    public List<EventoDTO> listarEventosDisponibles(){
        Date fechaActual = new Date();
        List<Evento> listaEventosDisponibles = this.eventoRepository.findEventosDisponibles(fechaActual);

        return this.convertirAListaDTO(listaEventosDisponibles);
    }


    public List<EventoDTO> findAll() {
        List<Evento> listaEventos = this.eventoRepository.findAll();
        return this.convertirAListaDTO(listaEventos);
    }

    public List<EventoDTO> listarEventosCreador(Integer id){
        List<Evento> listaEventosCreador = this.eventoRepository.findByIdCreador(id);

        return this.convertirAListaDTO(listaEventosCreador);
    }

    public List<EventoDTO> listarEventosCreadorFiltro(Integer id, String filtro){
        List<Evento> listaEventosCreador = this.eventoRepository.findByIdCreadorFiltro(id, filtro);

        return this.convertirAListaDTO(listaEventosCreador);
    }

    public Evento buscarEvento(Integer id){
        Evento evento = this.eventoRepository.findById(id).orElse(null);

        if(evento == null){
            return null;
        } else{
            return evento;
        }
    }

    public EventoDTO buscarEventoId(Integer id){
        Evento evento = this.eventoRepository.findById(id).orElse(null);

        if(evento == null){
            return null;
        } else{
            return evento.getDTO();
        }
    }

    public void eliminarEvento(Integer eventoid) {
        Evento evento = this.eventoRepository.findById(eventoid).orElse(null);

        this.eventoRepository.delete(evento);
    }
}
