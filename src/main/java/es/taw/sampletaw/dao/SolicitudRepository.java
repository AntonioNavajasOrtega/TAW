package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud,Integer> {


    @Query("select e from Solicitud e where e.empresaByEmpresaId.id = :id")
    List<Solicitud> findByEmpresa(int id);
}
