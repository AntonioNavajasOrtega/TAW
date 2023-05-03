package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversacionRepository extends JpaRepository<Conversacion,Integer> {


    @Query("select c from Conversacion c order by size(c.mensajesById) asc")
    List<Conversacion> ordenPorNumeroMensajesAsc();

    @Query("select c from Conversacion c order by c.mensajesById.size desc")
    List<Conversacion> ordenPorNumeroMensajesDesc();

    @Query("select c from Conversacion c order by c.fechaApertura asc")
    List<Conversacion> ordenPorFechaAperturaAsc();

    @Query("select c from Conversacion c order by c.fechaApertura desc ")
    List<Conversacion> ordenPorFechaAperturaDesc();

     @Query("select c from Conversacion c where c.clienteByCliente.nombre like concat('%',:usu,'%')")
    List<Conversacion> buscaPorUsuario(@Param("usu") String usuario);

    @Query("select c from Conversacion c order by c.fechaApertura desc, size(c.mensajesById) desc")
    List<Conversacion> ordenFechaAperturaDescYMensajesDesc();

    @Query("select c from Conversacion c order by c.fechaApertura asc, size(c.mensajesById) asc")

    List<Conversacion> ordenFechaAperturaAscYMensajesAsc();
    @Query("select c from Conversacion c order by c.fechaApertura desc, size(c.mensajesById) asc")

    List<Conversacion> ordenFechaAperturaDescYMensajesAsc();
    @Query("select c from Conversacion c order by c.fechaApertura asc, size(c.mensajesById) desc")

    List<Conversacion> ordenFechaAperturaAscYMensajesDesc();
    @Query("select c from Conversacion c  where c.clienteByCliente.nombre like concat('%',:usu,'%') order by c.fechaApertura desc, size(c.mensajesById) desc")

    List<Conversacion> ordenFechaAperturaDescYMensajesDescYBuscaUsuario(@Param("usu")String usuario);
    @Query("select c from Conversacion c  where c.clienteByCliente.nombre like concat('%',:usu,'%') order by c.fechaApertura asc, size(c.mensajesById) asc")

    List<Conversacion> ordenFechaAperturaAscYMensajesAscYBuscaUsuario(@Param("usu")String usuario);
    @Query("select c from Conversacion c  where c.clienteByCliente.nombre like concat('%',:usu,'%') order by c.fechaApertura desc, size(c.mensajesById) asc")

    List<Conversacion> ordenFechaAperturaDescYMensajesAscYBuscaUsuario(@Param("usu")String usuario);
    @Query("select c from Conversacion c  where c.clienteByCliente.nombre like concat('%',:usu,'%') order by c.fechaApertura asc, size(c.mensajesById) desc")

    List<Conversacion> ordenFechaAperturaAscYMensajesDescYBuscaUsuario(@Param("usu")String usuario);
    @Query("select c from Conversacion c  where c.clienteByCliente.nombre like concat('%',:usu,'%') order by size(c.mensajesById) asc")

    List<Conversacion> ordenPorNumeroMensajesAscYBuscaUsuario(@Param("usu")String usuario);
    @Query("select c from Conversacion c  where c.clienteByCliente.nombre like concat('%',:usu,'%') order by size(c.mensajesById) desc")

    List<Conversacion> ordenPorNumeroMensajesDescYBuscaUsuario(@Param("usu")String usuario);
    @Query("select c from Conversacion c  where c.clienteByCliente.nombre like concat('%',:usu,'%') order by c.fechaApertura asc")

    List<Conversacion> ordenPorFechaAperturaAscYBuscaUsuario(@Param("usu")String usuario);
    @Query("select c from Conversacion c  where c.clienteByCliente.nombre like concat('%',:usu,'%') order by c.fechaApertura desc")

    List<Conversacion> ordenPorFechaAperturaDescYBuscaUsuario(@Param("usu")String usuario);

    @Query("select c from Conversacion c where c.clienteByCliente.id = :idCliente and c.abierta = 1")
    List<Conversacion> buscaPorUsuarioConversacionesAbiertas(@Param("idCliente") Integer idcliente);

    @Query("select c from Conversacion c where c.empleadoByEmpleado.id = :id")
    List<Conversacion> buscaPorEmpleado(@Param("id") Integer id);
}
