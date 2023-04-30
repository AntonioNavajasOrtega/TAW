package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("select a from Cliente a where a.email = :email and a.contrasena = :contrasena")
    public Cliente autenticar (@Param("email") String email, @Param("contrasena")String contrasena);

    @Query("select distinct c.apellido from Cliente c where c.empresaByEmpresaId = :empresa")
    public List<String> clientesSocios(Empresa empresa);

    @Query("select c from Cliente c where c.mensajesById.size > 0")
    List<Cliente> buscarClientesConMensajes();
}
