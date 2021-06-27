package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {

    @Query("select e from Entrada e where e.eventoByIdEvento.id = :idEvento")
    public List<Entrada> findByIdEvento(@Param("idEvento") Integer id);

    @Query("SELECT e FROM Entrada e WHERE e.usuarioEventoByIdUsuario.id = :id")
    public List<Entrada> findByIdUsuario(@Param("id") Integer id);

}
