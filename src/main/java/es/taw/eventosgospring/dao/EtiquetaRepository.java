package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EtiquetaRepository extends JpaRepository<Etiqueta,Integer> {
    @Query("SELECT e FROM Etiqueta e where e.nombre = :nombre")
    public Etiqueta findByName(@Param("nombre") String nombre);
}
