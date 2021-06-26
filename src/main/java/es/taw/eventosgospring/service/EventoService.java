package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.EntradaRepository;
import es.taw.eventosgospring.dao.EventoRepository;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.entity.Entrada;
import es.taw.eventosgospring.entity.Evento;
import es.taw.eventosgospring.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class EventoService {

    private EventoRepository eventoRepository;
    private EntradaRepository entradaRepository;

    @Autowired
    public void setEntradaRepository(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

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
    /*
    public void crearEvento(EventoDTO nuevoEvento, Usuario creador) {
        Evento evento = new Evento();

        evento.setTitulo(nuevoEvento.getTitulo());
        evento.setAforo(nuevoEvento.getAforo());
        evento.setDescripcion(nuevoEvento.getDescripcion());
        evento.setCoste(nuevoEvento.getCoste());
        evento.setFechaEvento(nuevoEvento.getFechaEvento());
        evento.setFechaFinReservas(nuevoEvento.getFechaFinReservas());
        evento.setUsuarioByIdCreador(creador);
        evento.setMaximoEntradasUsuario(nuevoEvento.getMaximoEntradasUsuario());

    }

    public void editarEvento(EventoDTO nuevoEvento, Usuario creador) {
    }
    */


    public Map<EventoDTO, Integer> listarEventosAsistidosFiltro(String filtroEvento, Integer id) {
        List<Entrada> listaEntradas = this.entradaRepository.findByIdUsuario(id);
        Map<Evento, Integer> eventosMap = new HashMap<>();
        Map<EventoDTO, Integer> resultado = new HashMap<>();


        if(listaEntradas != null){
            for(Entrada e : listaEntradas){
                Evento evento;
                if(filtroEvento == null || filtroEvento.isEmpty()){
                    evento = this.eventoRepository.findById(id).orElse(null);
                } else{
                    evento = this.eventoRepository.findByIdAndSimilarName(e.getEventoByIdEvento().getId(), filtroEvento);
                }
                if (evento != null) {
                    if (!eventosMap.containsKey(evento)) {
                        eventosMap.put(evento, 1);
                    } else {
                        eventosMap.put(evento, eventosMap.get(evento) + 1);
                    }
                }
            }
        }
        for(Evento e : eventosMap.keySet()){
            resultado.put(e.getDTO(), eventosMap.get(e));
        }
        return resultado;
    }


        public Map<EventoDTO, Integer> listarEventosAsistidos(Integer id) {
            List<Entrada> listaEntradas = this.entradaRepository.findByIdUsuario(id);
            Map<Evento, Integer> eventosMap = new HashMap<>();
            Map<EventoDTO, Integer> resultado = new HashMap<>();
            Evento evento;

            if (listaEntradas != null) {
                for (Entrada e : listaEntradas) {
                    evento = this.eventoRepository.findById(e.getEventoByIdEvento().getId()).orElse(null);
                    if (evento != null) {
                        if (!eventosMap.containsKey(evento)) {
                            eventosMap.put(evento, 1);
                        } else {
                            eventosMap.put(evento, eventosMap.get(evento) + 1);
                        }
                    }
                }
            }
            for(Evento e : eventosMap.keySet()){
                resultado.put(e.getDTO(), eventosMap.get(e));
            }

            return resultado;
        }
}
