package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.MensajeRepository;
import es.taw.eventosgospring.dto.MensajeDTO;
import es.taw.eventosgospring.entity.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MensajeService {

    private MensajeRepository mensajeRepository;

    @Autowired
    public void setMensajeRepository(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    public List<MensajeDTO> getMensajeDTOByIDConversacion(Integer id) {
        List<Mensaje> lista = this.mensajeRepository.findByConversacionID(id);
        List<MensajeDTO> res = new ArrayList<>();

        for (Mensaje m : lista) {
            res.add(m.getDTO());
        }

        return res;
    }

}
