package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.MensajeRepository;
import es.taw.eventosgospring.dto.MensajeDTO;
import es.taw.eventosgospring.entity.Conversacion;
import es.taw.eventosgospring.entity.Mensaje;
import es.taw.eventosgospring.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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

    public Mensaje addMensaje(MensajeDTO mensajeDTO, Conversacion conversacion, Usuario usuario) {

        Mensaje mensaje = new Mensaje();
        mensaje.setFecha(mensajeDTO.getFecha());
        Time hora = new Time(mensajeDTO.getHora().getTime());
        mensaje.setHora(hora);
        mensaje.setTexto(mensajeDTO.getTexto());
        mensaje.setConversacionByIdConversacion(conversacion);
        mensaje.setUsuarioByIdUsuario(usuario);
        mensaje.setVisto(mensajeDTO.getVisto());

        this.mensajeRepository.save(mensaje);

        return mensaje;
    }

}
