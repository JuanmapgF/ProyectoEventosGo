package es.taw.eventosgospring.service;

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

    @Autowired
    public void setEventoEtiquetaRepository(EventoEtiquetaRepository eventoEtiqeuetaRepository) {
        this.eventoEtiquetaRepository = eventoEtiquetaRepository;
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
}
