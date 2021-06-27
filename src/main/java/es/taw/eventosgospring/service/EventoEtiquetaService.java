package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.EtiquetaRepository;
import es.taw.eventosgospring.dao.EventoEtiquetaRepository;
import es.taw.eventosgospring.dao.EventoRepository;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.EventoEtiquetaDTO;
import es.taw.eventosgospring.entity.Evento;
import es.taw.eventosgospring.entity.EventoEtiqueta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventoEtiquetaService {
    private EventoEtiquetaRepository eventoEtiquetaRepository;
    private EtiquetaRepository etiquetaRepository;
    private  EventoRepository eventoRepository;

    @Autowired
    public void setEventoEtiquetaRepository(EventoEtiquetaRepository eventoEtiqeuetaRepository) {
        this.eventoEtiquetaRepository = eventoEtiquetaRepository;
    }

    @Autowired
    public void setEtiquetaRepository(EtiquetaRepository etiquetaRepository){
        this.etiquetaRepository = etiquetaRepository;
    }

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    public List<EventoEtiquetaDTO> convertirAListaDTO(List<Integer> etiquetas){
        if(etiquetas!=null){
            List<EventoEtiquetaDTO> etiquetasDTO = new ArrayList<>();
            for(Integer e : etiquetas){
                EventoEtiqueta etiqueta= this.eventoEtiquetaRepository.findById(e).orElse(null);
                if(etiqueta != null){
                    etiquetasDTO.add(etiqueta.getDTO());
                }

            }
            return etiquetasDTO;
        } else{
            return null;
        }
    }

    public void guardarEventoEtiqueta(EventoEtiquetaDTO evet) {
        EventoEtiqueta eventoEtiqueta = new EventoEtiqueta();
        Evento evento = this.eventoRepository.findById(evet.getEventoByIdEvento().getId()).orElse(null);

        eventoEtiqueta.setEtiquetaByIdEtiqueta(this.etiquetaRepository.findByName(evet.getEtiquetaByIdEtiqueta().getNombre()));
        eventoEtiqueta.setEventoByIdEvento(evento);

        this.eventoEtiquetaRepository.save(eventoEtiqueta);

    }
}
