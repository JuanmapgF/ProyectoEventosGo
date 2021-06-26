package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.Conversacion;
import es.taw.eventosgospring.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversacionRepository extends JpaRepository<Conversacion, Integer> {

    @Query("SELECT c FROM Conversacion c WHERE c.usuarioByIdTeleoperador = :user OR c.usuarioByIdUsuario = :user")
    public List<Conversacion> findByUsuario(@Param("user") Usuario usuario);

}
