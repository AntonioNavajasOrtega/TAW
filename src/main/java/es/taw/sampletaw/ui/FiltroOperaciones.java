package es.taw.sampletaw.ui;

import java.sql.Timestamp;

/*
    @author Javier Serrano Contreras 100%
 */
public class FiltroOperaciones {
    Integer clienteid;
    String date;

    String cuentadestino;

    public FiltroOperaciones(){
        date ="";
        cuentadestino ="";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCuentadestino() {
        return cuentadestino;
    }

    public void setCuentadestino(String cuentadestino) {
        this.cuentadestino = cuentadestino;
    }

    public Integer getClienteid() {
        return clienteid;
    }

    public void setClienteid(Integer clienteid) {
        this.clienteid = clienteid;
    }
}
