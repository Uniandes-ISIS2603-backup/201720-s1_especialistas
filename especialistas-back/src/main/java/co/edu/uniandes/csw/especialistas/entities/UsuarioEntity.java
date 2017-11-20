/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ce.quintero
 */
@Entity
public class UsuarioEntity implements Serializable{
    /**
     * Id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /*
    *Atributo que representa el nombre del usuario
    */
    private String nombre;
    
    /*
    *Atributo que representa el numero de cedula del usuario
    */
    private int cedula;
    
    /*
    *Atributo que representa el nik del usuario
    */
    private String nick;
    
    /*
    *Atributo que representa la clave del usuario
    */
    private String pass;
    
     /*
    *Atributo que representa el rol del usuario
    */
    private String rol;
    
    /**
     * atributo que modela las citas del usuario
     */
    @PodamExclude
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<CitaEntity> citasMedicas = new ArrayList<>();
    
    
    /**
     * atributo que modela los pagos del usuario
     */
    @PodamExclude
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<PagoEntity> pagos = new ArrayList<>();
    
    /**
     * atributo que modela la targeta del usuario
     */
    @PodamExclude
    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TarjetaEntity tarjeta;
    
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
         if (tarjeta.getUsuario()!=this) {
             tarjeta.setUsuario(this);
         }
         this.tarjeta = tarjeta;
     }
     
  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
     
     /**
     * getter del atributo citas
     * @return una lista con las citas asociadas a un usuario
     */
    public List<CitaEntity> getCitas(){
        return this.citasMedicas;
    }
    
    /**
     * setter del atributo citas
     * @param citasMedicas 
     */
    public void setCitas(List<CitaEntity> citasMedicas){
        for (CitaEntity citasMedica : citasMedicas) {
            if (citasMedica.getUsuario()!=this) {
                citasMedica.setUsuario(this);
            }
        }
        this.citasMedicas = citasMedicas;
    }
    
    /**
     * getter del atributo citas
     * @return una lista con las citas asociadas a un usuario
     */
    public List<PagoEntity> getPagos(){
        return this.pagos;
    }
    
    /**
     * setter del atributo citas
     * @param pagos 
     */
    public void setPagos(List<PagoEntity> pagos){
        for (PagoEntity pago : pagos) {
            if(pago.getUsuario()!=this)
                pago.setUsuario(this);
        }
        this.pagos = pagos;
    }
    
    /**
     * agrega una cita individal al usuario
     * @param cita 
     */
    public void addCita(CitaEntity cita){
        this.citasMedicas.add(cita);
    }
    
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
     * getter del atributo nick
     * @return 
     */
    public String getNick(){
        return this.nick;
    }
    
    /**
     * setter del atributo nick
     * @param nick 
     */
    public void setNick(String nick){
        this.nick = nick;
    }
    
    /**
     * getter del atributo pass
     * @return 
     */
    public String getPass(){
        return this.pass;
    }
    
    /**
     * setter del atributo pass
     * @param pass 
     */
    public void setPass(String pass){
        this.pass = pass;
    }
    
    /**
     * getter del atributo rol
     * @return 
     */
    public String getRol(){
        return this.rol;
    }
    
    /**
     * setter del atributo rol
     * @param rol
     */
    public void setRol(String rol){
        this.rol = rol;
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
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj != null)
        {
            if(obj.getClass()!=this.getClass()){
                return false;
            }
            if (this.getId() != null && ((UsuarioEntity)obj).getId() != null) {
                return this.getId().equals(((UsuarioEntity)obj).getId());
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
    
}
