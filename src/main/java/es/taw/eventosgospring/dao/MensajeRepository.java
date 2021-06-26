package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje,Integer> {

    @Query("SELECT m FROM Mensaje m WHERE m.conversacionByIdConversacion.id = :id")
    public List<Mensaje> findByConversacionID(@Param("id") Integer idConversacion);

}
