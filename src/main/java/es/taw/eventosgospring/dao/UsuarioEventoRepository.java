package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.UsuarioEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioEventoRepository extends JpaRepository<UsuarioEvento, Integer> {
}
