package es.taw.sampletaw.dto;

import java.io.Serializable;
/**
 * @author Juan José Torres
 */

public class TipoEmpleadoDTO implements Serializable {

    private Integer id;

    private String tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
