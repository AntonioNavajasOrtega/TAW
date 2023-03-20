package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Integer> {
}
