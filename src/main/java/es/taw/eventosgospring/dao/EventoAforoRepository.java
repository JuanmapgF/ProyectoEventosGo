package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.EventoAforo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoAforoRepository extends JpaRepository<EventoAforo,Integer> {
}
