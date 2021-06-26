package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.ConversacionRepository;
import es.taw.eventosgospring.dao.UsuarioRepository;
import es.taw.eventosgospring.dto.ConversacionDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.entity.Conversacion;
import es.taw.eventosgospring.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversacionService {

    private ConversacionRepository conversacionRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setConversacionRepository(ConversacionRepository conversacionRepository) {
        this.conversacionRepository = conversacionRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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

}
