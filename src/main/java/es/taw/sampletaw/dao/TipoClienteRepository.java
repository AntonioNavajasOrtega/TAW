package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.EstadoCuenta;
import es.taw.sampletaw.entity.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoClienteRepository extends JpaRepository<TipoCliente, Integer> {

    @Query("select e from TipoCliente e where e.tipo = :str")
    TipoCliente findTipo(String str);
}
