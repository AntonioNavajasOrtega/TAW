package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("select a from Cliente a where a.email = :email and a.contrasena = :contrasena")
    public Cliente autenticar (@Param("email") String email, @Param("contrasena")String contrasena);
}
