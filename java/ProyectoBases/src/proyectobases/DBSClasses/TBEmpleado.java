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
public class TBEmpleado {
    
    String usuario,nombre,apellido,cargo,clave;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public TBEmpleado(String nombre, String apellido, String cargo, String clave,String usuario){
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.clave = clave;        
    }
    
    
}
