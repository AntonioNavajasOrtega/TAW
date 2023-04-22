package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Cuenta;
import es.taw.sampletaw.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta,Integer> {

    @Query("select c from Cuenta c where c.id != :id")
    List<Cuenta> findCuentas(@Param("id") Integer id);

    @Query("select t from Transaccion t where (t.cuentaByCuentaDestinoId in(select c from Cuenta c where c.clienteByClienteId.id = :id) and t.cantidad > 0) or (t.cuentaByCuentaOrigenId in(select c from Cuenta c where c.clienteByClienteId.id = :id) and t.cantidad < 0)")
    List<Transaccion> findClienteTrans(@Param("id") Integer id);

    @Query("select t from Transaccion t where (t.cuentaByCuentaOrigenId in(select c from Cuenta c where c.clienteByClienteId.id = :id) and t.cantidad < 0) order by t.fecha desc")
    List<Transaccion> findDestDateTrans(@Param("id") Integer id);

    @Query("select t from Transaccion t where t.cuentaByCuentaOrigenId in(select c from Cuenta c where c.clienteByClienteId.id = :id) and t.cantidad < 0")
    List<Transaccion> findDestTrans(@Param("id") Integer id);

    @Query("select t from Transaccion t where (t.cuentaByCuentaDestinoId in(select c from Cuenta c where c.clienteByClienteId.id = :id) and t.cantidad > 0) or (t.cuentaByCuentaOrigenId in(select c from Cuenta c where c.clienteByClienteId.id = :id) and t.cantidad < 0) order by t.fecha desc")
    List<Transaccion> findDateTrans(@Param("id") Integer id);
}
