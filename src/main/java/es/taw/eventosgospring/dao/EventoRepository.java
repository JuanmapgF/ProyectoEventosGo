package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @Query("SELECT e from Evento e WHERE e.fechaEvento >= :fechaActual")
    public List<Evento> findEventosDisponibles(@Param("fechaActual") Date fechaActual );

}
