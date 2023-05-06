package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.TipoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 * @author Juan José Torres Palomo
 */
public interface TipoEmpleadoRepository extends JpaRepository<TipoEmpleado,Integer> {
    @Query("select t from TipoEmpleado  t where lower(t.tipo) like CONCAT('%', lower(:tipo), '%') ")
    TipoEmpleado findTipoEmpleado(@Param("tipo") String tipo);


}
