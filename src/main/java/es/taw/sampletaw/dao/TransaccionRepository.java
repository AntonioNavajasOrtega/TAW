package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/*
    @author Javier Serrano Contreras 100%
 */
public interface TransaccionRepository extends JpaRepository<Transaccion,Integer> {
}
