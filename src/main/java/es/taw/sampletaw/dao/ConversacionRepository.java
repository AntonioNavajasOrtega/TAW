package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversacionRepository extends JpaRepository<Conversacion,Integer> {
}
