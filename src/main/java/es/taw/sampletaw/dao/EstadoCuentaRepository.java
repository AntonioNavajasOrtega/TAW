package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.EstadoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Javier Serrano Contreras 50%
 * @author Antonio Navjas Ortega 50%
 */
public interface EstadoCuentaRepository extends JpaRepository<EstadoCuenta,Integer> {
    @Query("select e from EstadoCuenta e where e.tipo = 'Bloqueada'")
    EstadoCuenta findBloq();

    @Query("select e from EstadoCuenta e where e.tipo = 'Activa'")
    EstadoCuenta findAct();
}
