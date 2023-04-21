package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    @Query("select m from Mensaje m where m.clienteByEmisorCliente =: usuario or m.clienteByReceptorCliente = :usuario")

    List<Mensaje> mensajesCuyoUsuarioEsEmisorOReceptor(@Param("usuario") Cliente usuario);
}
