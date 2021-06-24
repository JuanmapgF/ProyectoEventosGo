package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.UsuarioEventoRepository;
import es.taw.eventosgospring.dao.UsuarioRepository;
import es.taw.eventosgospring.dto.UsuarioEventoDTO;
import es.taw.eventosgospring.entity.Usuario;
import es.taw.eventosgospring.entity.UsuarioEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UsuarioEventoService {

    private UsuarioEventoRepository usuarioEventoRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setUsuarioEventoRepository(UsuarioEventoRepository usuarioEventoRepository) {
        this.usuarioEventoRepository = usuarioEventoRepository;
    }

    public UsuarioEventoDTO buscarUsuarioEventoId(Integer id){
        UsuarioEvento usuarioEvento = this.usuarioEventoRepository.findById(id).orElse(null);
        if(usuarioEvento != null){
            return usuarioEvento.getDTO();
        } else{
            return null;
        }
    }

    public void guardarUsuarioEvento(UsuarioEventoDTO dto){
        UsuarioEvento usuarioEvento = new UsuarioEvento();
        Usuario usuario =  this.usuarioRepository.findById(dto.getId()).orElse(null);

        usuarioEvento.setId(usuario.getId());
        // usuarioEvento.setUsuarioById(usuario);
        usuarioEvento.setApellidos(dto.getApellidos());
        usuarioEvento.setDomicilio(dto.getDomicilio());
        usuarioEvento.setFechaNacimiento((Date) dto.getFechaNacimiento());
        usuarioEvento.setCiudad(dto.getCiudad());
        usuarioEvento.setSexo(dto.getSexo());

        this.usuarioEventoRepository.save(usuarioEvento);

        /*
        usuario.setUsuarioEventoById(usuarioEvento);

        this.usuarioRepository.save(usuario);
        */

    }
}
