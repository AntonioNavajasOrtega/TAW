package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
}
