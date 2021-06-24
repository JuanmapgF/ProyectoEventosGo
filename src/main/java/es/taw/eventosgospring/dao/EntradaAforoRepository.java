package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.EntradaAforo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaAforoRepository extends JpaRepository<EntradaAforo, Integer> {
}
