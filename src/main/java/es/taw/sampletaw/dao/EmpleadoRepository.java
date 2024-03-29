package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 * @author Juan José Torres 1 query
 * @author Javier Serrano Contreras 1 query
 */
public interface EmpleadoRepository extends JpaRepository<Empleado , Integer> {

    @Query("select a from Empleado a where a.nombreUsuario = :email and a.contrasena = :contrasena")
    public Empleado autenticar (@Param("email") String email, @Param("contrasena")String contrasena);


    @Query("select e from Empleado e where e.tipoEmpleadoByTipo.tipo = 'Gestor'")
    Empleado findGestor();

}
