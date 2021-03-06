package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.*;
import es.taw.eventosgospring.dto.EtiquetaDTO;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.EventoEtiquetaDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.entity.*;
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
    private UsuarioRepository usuarioRepository;
    private EventoEtiquetaRepository eventoEtiquetaRepository;

    @Autowired
    public void setEventoEtiquetaRepository(EventoEtiquetaRepository eventoEtiquetaRepository) {
        this.eventoEtiquetaRepository = eventoEtiquetaRepository;
    }

    @Autowired
    public void setEntradaRepository(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
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

        for(Entrada e : this.entradaRepository.findAll()){
            if(e.getEventoByIdEvento().getId() == evento.getId()){
                this.entradaRepository.delete(e);
            }
        }

        for(EventoEtiqueta evet : this.eventoEtiquetaRepository.findAll()){
            if(evet.getEventoByIdEvento().getId() == evento.getId()){
                this.eventoEtiquetaRepository.delete(evet);
            }
        }

        this.eventoRepository.delete(evento);


    }


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

    public Integer guardarEvento(EventoDTO nuevoEvento, UsuarioDTO usuario) {
        Evento evento;
        Usuario creador = this.usuarioRepository.findById(usuario.getId()).orElse(null);

        if(nuevoEvento.getId() == null){
            evento = new Evento();
        }else{
            evento = this.buscarEvento(nuevoEvento.getId());
        }

        List<EventoEtiqueta> listaEventoEtiquetas = new ArrayList<>();
        for(Integer id : nuevoEvento.getEventoEtiquetasById()){
            EventoEtiqueta evet = this.eventoEtiquetaRepository.findById(id).orElse(null);
            if(evet!=null) {
                listaEventoEtiquetas.add(evet);
            }
        }

        List<Entrada> listaEntradas = new ArrayList<>();
        for(Integer id : nuevoEvento.getEntradasById()){
            Entrada ent = this.entradaRepository.findById(id).orElse(null);
            if(ent!=null){
                listaEntradas.add(ent);
            }
        }

        evento.setTitulo(nuevoEvento.getTitulo());
        evento.setDescripcion(nuevoEvento.getDescripcion());
        evento.setCoste(nuevoEvento.getCoste());
        evento.setAforo(nuevoEvento.getAforo());
        evento.setMaximoEntradasUsuario(nuevoEvento.getMaximoEntradasUsuario());
        evento.setFechaEvento(nuevoEvento.getFechaEvento());
        evento.setFechaFinReservas(nuevoEvento.getFechaFinReservas());
        evento.setUsuarioByIdCreador(creador);
        evento.setEventoEtiquetasById(listaEventoEtiquetas);
        evento.setEntradasById(listaEntradas);

        this.eventoRepository.save(evento);

        List<Evento> listaEventos = creador.getEventosById();
        listaEventos.add(evento);
        creador.setEventosById(listaEventos);
        this.usuarioRepository.save(creador);

        return evento.getId();
    }
}
