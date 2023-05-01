package es.taw.sampletaw.dto;

import es.taw.sampletaw.entity.TipoCliente;

public class TipoClienteDTO {
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
