/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author ce.quintero
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{
    
    /*
    *Atributo que representa el nombre del usuario
    */
    private String nombre;
    
    /*
    *Atributo que representa el numero de cedula del usuario
    */
    private int cedula;
    
    @OneToMany(mappedBy="usuario")
    @JoinColumn(name="CITA_ID")
    private List <CitaEntity> citasMedicas;
    
    
    //Getters and setters
    public List<CitaEntity> getCitas(){
        return this.citasMedicas;
    }
    
    public void setCitas(List<CitaEntity> citasMedicas){
        this.citasMedicas = citasMedicas;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public int getCedula(){
        return this.cedula;
    }
    
    public void setCedula(int cedula){
        this.cedula = cedula;
    }
}
