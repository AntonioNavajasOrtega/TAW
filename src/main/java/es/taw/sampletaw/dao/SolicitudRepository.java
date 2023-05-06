package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author Javier Serrano Contreras 70%
 */
public interface SolicitudRepository extends JpaRepository<Solicitud,Integer> {

    @Query("select e from Solicitud e where e.empresaByEmpresaId.id = :id")
    List<Solicitud> findByEmpresa(int id);

    @Query("select s from Solicitud s where s.empleadoByEmpleadoId.id = :id")
    List<Solicitud> solicitudesEmpleado(@Param("id") Integer id);

    @Query("select s from Solicitud s where s.clienteByClienteId.id = :id")
    List<Solicitud> findSolicitudCliente(int id);
}
