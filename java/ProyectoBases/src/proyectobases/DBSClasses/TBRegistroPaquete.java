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
public class TBRegistroPaquete {
    String idCliente, nombre,apellido;
    
    public TBRegistroPaquete(String idCliente, String nombre, String apellido){
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
