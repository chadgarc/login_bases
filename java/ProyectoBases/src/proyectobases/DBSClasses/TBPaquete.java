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
public class TBPaquete {
    int idPaquete;
    String nombre,descripcion;

    public TBPaquete(int idPaquete,String nombre,String descripcion){
        this.idPaquete = idPaquete;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
