package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.UsuarioEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioEventoRepository extends JpaRepository<UsuarioEvento, Integer> {

    @Query("SELECT u FROM UsuarioEvento u WHERE u.sexo = :masc OR u.sexo = :fem OR u.sexo = :otro")
    public List<UsuarioEvento> filtroSexo(@Param("masc") int masc, @Param("fem") int fem, @Param("otro") int otro);

}
