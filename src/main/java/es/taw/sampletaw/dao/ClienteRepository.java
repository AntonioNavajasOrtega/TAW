package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    @Query("select a from ClienteEntity a where a.email = :email and a.contrasena = :contrasena")
    public ClienteEntity autenticar (@Param("email") String email, @Param("contrasena")String contrasena);
}
