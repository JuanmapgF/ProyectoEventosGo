package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {
}
