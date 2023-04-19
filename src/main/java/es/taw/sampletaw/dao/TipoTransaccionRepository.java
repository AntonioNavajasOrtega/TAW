package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.TipoTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoTransaccionRepository extends JpaRepository<TipoTransaccion,Integer> {
}
