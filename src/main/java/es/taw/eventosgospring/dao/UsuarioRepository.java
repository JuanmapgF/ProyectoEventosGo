package es.taw.eventosgospring.dao;

import es.taw.eventosgospring.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contrasena = :pass")
    public Usuario findByCorreoPass(@Param("correo") String email, @Param("pass") String pass);
}
