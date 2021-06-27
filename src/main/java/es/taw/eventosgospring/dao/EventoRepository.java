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

    @Query("SELECT e from Evento e WHERE e.usuarioByIdCreador.id = :id")
    public List<Evento> findByIdCreador(@Param("id") Integer id);

    @Query("SELECT e from Evento e WHERE e.usuarioByIdCreador.id = :id AND e.titulo LIKE %:filtro%")
    public List<Evento> findByIdCreadorFiltro(@Param("id") Integer id, @Param("filtro") String filtro);

    @Query("SELECT e FROM Evento e WHERE e.id = :id AND e.titulo LIKE %:filtro%")
    public Evento findByIdAndSimilarName(@Param("id") Integer id, @Param("filtro") String filtro);
}

