package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.EstudioRepository;
import es.taw.eventosgospring.dao.UsuarioEventoRepository;
import es.taw.eventosgospring.dao.UsuarioRepository;
import es.taw.eventosgospring.dto.EstudioDTO;
import es.taw.eventosgospring.dto.UsuarioEventoDTO;
import es.taw.eventosgospring.entity.Estudio;
import es.taw.eventosgospring.entity.Usuario;
import es.taw.eventosgospring.entity.UsuarioEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstudioService {
    
    private EstudioRepository estudioRepository;
    private UsuarioEventoRepository usuarioEventoRepository;
    private UsuarioRepository usuarioRepository;


    @Autowired
    public void setEstudioRepository(EstudioRepository estudioRepository) {
        this.estudioRepository = estudioRepository;
    }

    @Autowired
    public void setUsuarioEventoRepository(UsuarioEventoRepository usuarioEventoRepository) {
        this.usuarioEventoRepository = usuarioEventoRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<EstudioDTO> getEstudioDTOList(Usuario u) {
        List<EstudioDTO> estudioList = new ArrayList<>();
        for(Estudio e : this.estudioRepository.getEstudioListByUser(u)) {
            estudioList.add(e.getDTO());
        }
        return estudioList;
    }

    public void deleteEstudioById(Integer id, Usuario u) {
        Estudio estudio = this.estudioRepository.findById(id).orElse(null);
        if(estudio != null) {
            u.getEstudiosById().remove(estudio);
            this.usuarioRepository.save(u);
            this.estudioRepository.delete(estudio);
        }
    }

    public Integer getResultadoEstudio(Integer anio, Integer edad_min, Integer edad_max, Integer masc, Integer fem, Integer otro, String ciudad) {

        List<UsuarioEventoDTO> res = new ArrayList<>();
        List<UsuarioEvento> aux = this.usuarioEventoRepository.findAll();

        if (edad_min <= edad_max) {
            aux = this.getFiltroEdad(edad_min, edad_max, aux);
            
            if (masc != -1 || fem != -1 || otro != -1) {
                //res = this.usuarioEventoRepositor
            }
            
        }

        return 0;
    }

    private List<UsuarioEvento> getFiltroEdad(Integer min, Integer max, List<UsuarioEvento> l) {

        List<UsuarioEvento> res = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
        for (UsuarioEvento u : l) {
            int x = Period.between(LocalDate.of(Integer.parseInt(sdf.format(u.getFechaNacimiento())), Integer.parseInt(sdf1.format(u.getFechaNacimiento())), Integer.parseInt(sdf2.format(u.getFechaNacimiento()))), LocalDate.now()).getYears();
            if (min <= x && x <= max) {
                res.add(u);
            }
        }

        return res;
    }

    //private List<UsuarioEvento> getFiltroSexo()
}
