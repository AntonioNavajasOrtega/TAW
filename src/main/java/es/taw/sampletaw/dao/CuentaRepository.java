package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta,Integer> {

    @Query("select c from Cuenta c where c.id != :id")
    List<Cuenta> findCuentas(@Param("id") Integer id);
}
