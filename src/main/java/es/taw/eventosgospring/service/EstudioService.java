package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.EstudioRepository;
import es.taw.eventosgospring.dao.UsuarioEventoRepository;
import es.taw.eventosgospring.dto.EstudioDTO;
import es.taw.eventosgospring.dto.UsuarioEventoDTO;
import es.taw.eventosgospring.entity.Estudio;
import es.taw.eventosgospring.entity.Usuario;
import es.taw.eventosgospring.entity.UsuarioEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudioService {
    
    private EstudioRepository estudioRepository;
    private UsuarioEventoRepository usuarioEventoRepository;

    @Autowired
    public void setEstudioRepository(EstudioRepository estudioRepository) {
        this.estudioRepository = estudioRepository;
    }

    @Autowired
    public void setUsuarioEventoRepository(UsuarioEventoRepository usuarioEventoRepository) {
        this.usuarioEventoRepository = usuarioEventoRepository;
    }

    public List<EstudioDTO> getEstudioDTOList(Usuario u) {
        List<EstudioDTO> estudioList = new ArrayList<>();
        for(Estudio e : this.estudioRepository.getEstudioListByUser(u)) {
            estudioList.add(e.getDTO());
        }
        return estudioList;
    }

    public Integer getResultadoEstudio(Integer anio, Integer edad_min, Integer edad_max, String masc, String fem, String otro, String ciudad) {

        List<UsuarioEventoDTO> res = new ArrayList<>();
        List<UsuarioEvento> aux = this.usuarioEventoRepository.findAll();
        for (UsuarioEvento ue : aux) {
            res.add(ue.getDTO());
        }

        if (edad_min <= edad_max) {

        }

        return 0;
    }

    //private List<UsuarioEventoDTO> getFiltroEdad(Integer emin, Integer emax, List<UsuarioEve>)
}
