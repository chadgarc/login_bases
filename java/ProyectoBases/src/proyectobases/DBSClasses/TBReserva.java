/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobases.DBSClasses;

/**
 *
 * @author Cesar
 */
public class TBReserva {
    String idReserva,idCliente,NumeroVuelos,clase,idPaquete,idVuelo;

    public TBReserva(String idReserva, String idCliente, String NumeroVuelos, String clase, String idPaquete, String idVuelo) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.NumeroVuelos = NumeroVuelos;
        this.clase = clase;
        this.idPaquete = idPaquete;
        this.idVuelo = idVuelo;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumeroVuelos() {
        return NumeroVuelos;
    }

    public void setNumeroVuelos(String NumeroVuelos) {
        this.NumeroVuelos = NumeroVuelos;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(String idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(String idVuelo) {
        this.idVuelo = idVuelo;
    }
}
