package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.UsuarioRepository;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.entity.Evento;
import es.taw.eventosgospring.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    protected List<UsuarioDTO> convertirAListaDTO(List<Usuario> usuarios){
        if(usuarios!=null){
            List<UsuarioDTO> usuariosDTO = new ArrayList<>();
            for(Usuario u : usuarios){
                usuariosDTO.add(u.getDTO());
            }
            return usuariosDTO;
        } else{
            return null;
        }
    }
    public UsuarioDTO comprobarCredenciales(String correo, String pass){
        Usuario user = this.usuarioRepository.findByCorreoPass(correo, pass);
        if(user!=null){
            return user.getDTO();
        } else{
            return null;
        }
    }

    public UsuarioDTO buscarUsuarioId(Integer id){
        Usuario user = this.usuarioRepository.findById(id).orElse(null);
        if(user != null){
            return user.getDTO();
        } else{
            return null;
        }
    }

    public Usuario buscarUsuario(Integer id) {
        Usuario user = this.usuarioRepository.findById(id).orElse(null);
        if(user != null){
            return user;
        }else{
            return null;
        }
    }

    public Integer guardarUsuario(UsuarioDTO dto){

        Usuario usuario;
        if(dto.getId() == null){
            usuario = new Usuario();
        } else{
            usuario = this.usuarioRepository.findById(dto.getId()).orElse(new Usuario());
        }

        usuario.setRol(dto.getRol());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(dto.getContrasena());
        usuario.setNombre(dto.getNombre());

        this.usuarioRepository.save(usuario);

        return usuario.getId();
    }


    public List<UsuarioDTO> findAll() {
        List<Usuario> listaUsuarios = this.usuarioRepository.findAll();
        return this.convertirAListaDTO(listaUsuarios);
    }
}
