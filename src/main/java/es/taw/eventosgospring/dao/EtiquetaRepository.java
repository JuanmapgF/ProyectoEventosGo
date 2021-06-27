package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtiquetaRepository extends JpaRepository<Etiqueta,Integer> {
}
