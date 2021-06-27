package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.EtiquetaRepository;
import es.taw.eventosgospring.dto.EtiquetaDTO;
import es.taw.eventosgospring.entity.Etiqueta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EtiquetaService {
    private EtiquetaRepository etiquetaRepository;

    @Autowired
    public void setEtiquetaRepository(EtiquetaRepository etiquetaRepository){
        this.etiquetaRepository = etiquetaRepository;
    }


    public EtiquetaDTO findByName(String s) {
        Etiqueta etiqueta = this.etiquetaRepository.findByName(s);
        if(etiqueta != null){
            return etiqueta.getDTO();
        }else{
            return null;
        }
    }

    public void crearEtiqueta(EtiquetaDTO et) {
        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setNombre(et.getNombre());
        this.etiquetaRepository.save(etiqueta);
    }

    public EtiquetaDTO findById(Integer id){
        Etiqueta et = this.etiquetaRepository.findById(id).orElse(null);
        if(et != null){
            return et.getDTO();
        } else{
            return null;
        }
    }
}
