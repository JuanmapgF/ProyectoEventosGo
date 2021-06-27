package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.ConversacionRepository;
import es.taw.eventosgospring.dao.MensajeRepository;
import es.taw.eventosgospring.dao.UsuarioRepository;
import es.taw.eventosgospring.dto.ConversacionDTO;
import es.taw.eventosgospring.dto.MensajeDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.entity.Conversacion;
import es.taw.eventosgospring.entity.Mensaje;
import es.taw.eventosgospring.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConversacionService {

    private ConversacionRepository conversacionRepository;
    private UsuarioRepository usuarioRepository;
    private MensajeService mensajeService;

    @Autowired
    public void setConversacionRepository(ConversacionRepository conversacionRepository) {
        this.conversacionRepository = conversacionRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setMensajeService(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    public List<ConversacionDTO> getListaConversacionesUsuario(UsuarioDTO usuarioDTO) {
        List<ConversacionDTO> res = new ArrayList<>();
        Usuario usuario = this.usuarioRepository.findById(usuarioDTO.getId()).orElse(null);
        for (Conversacion c : this.conversacionRepository.findByUsuario(usuario)) {
            res.add(c.getDTO());
        }

        return res;
    }

    public List<UsuarioDTO> getListaUsuariosConversacion(UsuarioDTO usuarioDTO, List<ConversacionDTO> conversaciones) {
        List<UsuarioDTO> res = new ArrayList<>();

        for (ConversacionDTO c : conversaciones) {
            if (usuarioDTO.getRol() == 4) {
                res.add(this.usuarioRepository.findById(c.getUsuarioByIdTeleoperador()).orElse(null).getDTO());
            } else {
                res.add(this.usuarioRepository.findById(c.getUsuarioByIdUsuario()).orElse(null).getDTO());
            }

        }

        return res;

    }

    public ConversacionDTO getConversacionDTO(Integer id) {
        Optional<Conversacion> opt = this.conversacionRepository.findById(id);
        Conversacion conversacion = opt.get();

        return conversacion.getDTO();
    }

    public void enviarMensaje(MensajeDTO mensajeDTO, UsuarioDTO usuarioDTO) {
        Optional<Conversacion> opt = this.conversacionRepository.findById(mensajeDTO.getConversacionByIdConversacion());
        Conversacion conversacion = opt.get();

        Optional<Usuario> opt2 = this.usuarioRepository.findById(usuarioDTO.getId());
        Usuario usuario = opt2.get();

        Mensaje mensaje = this.mensajeService.addMensaje(mensajeDTO, conversacion, usuario);

        conversacion.getMensajesById().add(mensaje);

        this.conversacionRepository.save(conversacion);
    }

}
