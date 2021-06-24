package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversacionRepository extends JpaRepository<Conversacion, Integer> {
}
