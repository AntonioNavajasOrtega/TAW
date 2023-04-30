package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.EmpleadoRepository;
import es.taw.sampletaw.dto.EmpleadoDTO;
import es.taw.sampletaw.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {
    @Autowired
    protected EmpleadoRepository empleadoRepository;
    public EmpleadoDTO autenticar(String email, String contrasena) {
        Empleado empleado = this.empleadoRepository.autenticar(email,contrasena);
        return (empleado == null ? null : empleado.toDTO());
    }
}
