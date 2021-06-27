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

    public Integer asignarTeleoperador(String asunto, UsuarioDTO usuarioDTO) {
        List<Usuario> teleoperadores = this.usuarioRepository.findByRol(2);

        Optional<Usuario> opt2 = this.usuarioRepository.findById(usuarioDTO.getId());
        Usuario usuario = opt2.get();

        if (teleoperadores == null || teleoperadores.isEmpty()) {
            return 1;
        } else {
            Usuario tlp = teleoperadores.get(0);
            List<Conversacion> conv = new ArrayList<>();
            conv.addAll(teleoperadores.get(0).getConversacionsById());
            conv.addAll(teleoperadores.get(0).getConversacionsById_0());
            int num = conv.size();
            for (int i = 1; i < teleoperadores.size(); i++) {
                List<Conversacion> conversaciones = new ArrayList<>();
                conversaciones.addAll(teleoperadores.get(i).getConversacionsById());
                conversaciones.addAll(teleoperadores.get(i).getConversacionsById_0());
                if (num > conversaciones.size()) {
                    tlp = teleoperadores.get(i);
                    num = conversaciones.size();
                }
            }

            Conversacion c = new Conversacion();
            c.setAsunto(asunto);
            c.setUsuarioByIdTeleoperador(tlp);
            c.setUsuarioByIdUsuario(usuario);
            List<Mensaje> listaMensaje = new ArrayList<>();
            c.setMensajesById(listaMensaje);

            List<Conversacion> listaC = new ArrayList<>();
            listaC.addAll(usuario.getConversacionsById());
            listaC.addAll(usuario.getConversacionsById_0());
            listaC.add(c);
            usuario.setConversacionsById(listaC);

            List<Conversacion> listaC2 = new ArrayList<>();
            listaC2.addAll(tlp.getConversacionsById_0());
            listaC2.addAll(tlp.getConversacionsById());
            listaC2.add(c);
            tlp.setConversacionsById(listaC2);

            this.conversacionRepository.save(c);
            this.usuarioRepository.save(usuario);
            this.usuarioRepository.save(tlp);

            return 0;

        }
    }

    public List<ConversacionDTO> getTodasLasConversaciones() {
        List<Conversacion> lista = this.conversacionRepository.findAll();

        List<ConversacionDTO> res = new ArrayList<>();
        for (Conversacion c : lista) {
            res.add(c.getDTO());
        }

        return res;
    }

    public List<UsuarioDTO> getTodosLosUsuarios() {
        List<Usuario> l = this.usuarioRepository.findAll();

        List<UsuarioDTO> res = new ArrayList<>();
        for (Usuario u : l) {
            res.add(u.getDTO());
        }

        return res;
    }

}
