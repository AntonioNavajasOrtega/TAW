package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.TipoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoSolicitudRepository extends JpaRepository<TipoSolicitud,Integer> {

    @Query("select t from TipoSolicitud t where t.tipo = 'Activacion' ")
    TipoSolicitud findActivar();

    @Query("select t from TipoSolicitud t where t.tipo != 'Activacion' ")
    TipoSolicitud findSolicitarCuent();

}
