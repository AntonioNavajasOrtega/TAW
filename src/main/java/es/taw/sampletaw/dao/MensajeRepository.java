package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Juan José Torres 50%
 * @author Javier Serrano 50%
 */


import java.util.List;

/*
    @author Javier Serrano Contreras 40%
 */
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    @Query("select m from Mensaje m where m.clienteByEmisorCliente.id =: id or m.clienteByReceptorCliente.id = :id")

    List<Mensaje> mensajesCuyoUsuarioEsEmisorOReceptor(@Param("id") Integer usuario);

    @Query("select m from Mensaje m where m.conversacionByConversacion.id = :id")
    List<Mensaje> mensajesPorConversacion(@Param("id") Integer id);

    @Query("select m from Mensaje m where m.empleadoByEmisorEmpleado.id = :id")
    List<Mensaje> mensajesEmpleadoEmisor(@Param("id") Integer id);

    @Query("select m from Mensaje m where m.empleadoByReceptorEmpleado.id = :id")
    List<Mensaje> mensajesEmpleadoReceptor(@Param("id") Integer id);
}
