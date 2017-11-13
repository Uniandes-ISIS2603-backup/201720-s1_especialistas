/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlTransient;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author rc.tejon
 */
@Entity
public class MedicamentoEntity implements Serializable{
    /**
     * Id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double precio;
    private String nombre;

    @PodamExclude
    @ManyToMany
    private List<FarmaciaEntity> farmacias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void agregarFarmacia(FarmaciaEntity farmacia)
    {
        if(farmacias==null)
        {
            farmacias=new ArrayList<FarmaciaEntity>();
        }
        farmacias.add(farmacia);
    }

    public void eliminarFarmcia(FarmaciaEntity farmacia)throws BusinessLogicException
    {

        if(farmacias==null || !farmacias.contains(farmacia))
        {
            throw new BusinessLogicException("no existe el medicamento");
        }
        farmacias.remove(farmacia);
    }

    @XmlTransient
    public List<FarmaciaEntity> getFarmacias() {
        return farmacias;
    }

    public void setFarmacias(List<FarmaciaEntity> farmacias) {
        this.farmacias = farmacias;
    } 
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj != null)
        {
            if(obj.getClass()!=this.getClass()){
                return false;
            }
            if (this.getId() != null && ((MedicamentoEntity)obj).getId() != null) {
                return this.getId().equals(((MedicamentoEntity)obj).getId());
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