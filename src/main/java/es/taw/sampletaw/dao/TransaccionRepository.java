package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion,Integer> {
}
