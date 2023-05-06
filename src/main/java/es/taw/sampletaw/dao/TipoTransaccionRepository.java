package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.TipoTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Javier Serrano Contreras 100%
 */
public interface TipoTransaccionRepository extends JpaRepository<TipoTransaccion,Integer> {

    @Query("select t from TipoTransaccion t where t.tipo = 'Pago'")
    TipoTransaccion findTrans();

    @Query("select t from TipoTransaccion t where t.tipo = 'Cambio'")
    TipoTransaccion findCambio();
}
