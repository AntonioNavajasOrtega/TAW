package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Antonio Navajas Ortega
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
}
