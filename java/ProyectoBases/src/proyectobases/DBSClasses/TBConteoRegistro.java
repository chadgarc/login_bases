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
public class TBConteoRegistro {
    String nombre;
    int conteo;
    
    public TBConteoRegistro(String nombre, int conteo){
        this.nombre = nombre;
        this.conteo = conteo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }
}
