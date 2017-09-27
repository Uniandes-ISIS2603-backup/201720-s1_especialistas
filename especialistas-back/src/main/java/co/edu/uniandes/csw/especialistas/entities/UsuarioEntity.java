/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

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
    
    /**
     * atributo que modela las citas del usuario
     */
//    @PodamExclude
//    @OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List <CitaEntity> citasMedicas;
    
    /**
     * atributo que modela la targeta del usuario
     */
    @PodamExclude
    @OneToOne(mappedBy="usuario")
    private TarjetaEntity tarjeta;
    
     
//    /**
//     * Ubicacion del usuario
//     */
//    @PodamExclude
//    @OneToOne(mappedBy = "usuario")
//    private UbicacionEntity ubicacion;
    
    //Getters and setters
    /**
      * getter del atributo usuario
      * @return usuario asociado
      */
     public TarjetaEntity getTarjeta(){
         return this.tarjeta;
     }
     
     /**
      * setter del atributo usuario
      * @param tarjeta 
      */
     public void setTarjeta(TarjetaEntity tarjeta){
         this.tarjeta = tarjeta;
     }
     
//     /**
//      * getter del atributo ubicacion
//      * @return 
//      */
//     public UbicacionEntity getUbicacion(){
//         return this.ubicacion;
//     }
//     
//     /**
//      * setter del atributo ubicacion
//      * @param ubicacion 
//      */
//     public void setUbicacion(UbicacionEntity ubicacion){
//         this.ubicacion = ubicacion;
//     }
//    
//    /**
//     * getter del atributo citas
//     * @return una lista con las citas asociadas a un usuario
//     */
//    public List<CitaEntity> getCitas(){
//        return this.citasMedicas;
//    }
//    
//    /**
//     * setter del atributo citas
//     * @param citasMedicas 
//     */
//    public void setCitas(List<CitaEntity> citasMedicas){
//        this.citasMedicas = citasMedicas;
//    }
    
    /**
     * getter del atributo nombre
     * @return 
     */
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * setter del atributo nombre
     * @param nombre 
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    /**
     * getter del atributo cedula
     * @return 
     */
    public int getCedula(){
        return this.cedula;
    }
    
    /**
     * setter del atributo cedula
     * @param cedula 
     */
    public void setCedula(int cedula){
        this.cedula = cedula;
    }
}
