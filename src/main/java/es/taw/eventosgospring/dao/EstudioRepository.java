package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.Estudio;
import es.taw.eventosgospring.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstudioRepository extends JpaRepository<Estudio,Integer> {
    @Query("SELECT e FROM Estudio e WHERE e.usuarioByIdAnalista = :user")
    public List<Estudio> getEstudioListByUser(@Param("user")Usuario usuario);
}
