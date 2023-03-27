package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado , Integer> {
}
