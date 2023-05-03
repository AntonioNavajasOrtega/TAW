package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.TipoCliente;
import es.taw.sampletaw.entity.Tipoclienterelacionado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoclienterelacionadoRepository extends JpaRepository<Tipoclienterelacionado,Integer> {

    @Query("select t from Tipoclienterelacionado t where t.clienteByClienteId.id = :id")
    Tipoclienterelacionado findByCliente(Integer id);

    @Query("select e from Tipoclienterelacionado e where e.clienteByClienteId.empresaByEmpresaId.id = :id")
    List<Tipoclienterelacionado> findByEmpresa(Integer id);

    @Query("select t from Tipoclienterelacionado t where t.tipoClienteByTipo.tipo = 'Propietario'")
    Tipoclienterelacionado findPropietario();

}
