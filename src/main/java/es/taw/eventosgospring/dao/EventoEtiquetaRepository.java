package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.EventoEtiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoEtiquetaRepository extends JpaRepository<EventoEtiqueta,Integer> {
}
