package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje,Integer> {
}
