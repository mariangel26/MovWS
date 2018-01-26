/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author mariangelperez
 */
public class Usuario {
    private String _nombre;
    private String _apellido;
    private String _cedula;
    private String _numero;
    private String _numero2;

    public Usuario(String _nombre, String _apellido, String _cedula, String _numero, String _numero2) {
        this._nombre = _nombre;
        this._apellido = _apellido;
        this._cedula = _cedula;
        this._numero = _numero;
        this._numero2 = _numero2;
    }
    
    public Usuario(){}

    public String getNombre() {
        return _nombre;
    }

    public String getApellido() {
        return _apellido;
    }

    public String getCedula() {
        return _cedula;
    }

    public String getNumero() {
        return _numero;
    }

    public String getNumero2() {
        return _numero2;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setApellido(String _apellido) {
        this._apellido = _apellido;
    }

    public void setCedula(String _cedula) {
        this._cedula = _cedula;
    }

    public void setNumero(String _numero) {
        this._numero = _numero;
    }

    public void setNumero2(String _numero2) {
        this._numero2 = _numero2;
    }
    

}
