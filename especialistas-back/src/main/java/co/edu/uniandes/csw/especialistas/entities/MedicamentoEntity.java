/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author rc.tejon
 */
@Entity
public class MedicamentoEntity extends BaseEntity{
    
    private double precio;
    private String nombre;

    @PodamExclude
    @ManyToMany
    private List<FarmaciaEntity> farmacias;

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

    public List<FarmaciaEntity> getFarmacias() {
        return farmacias;
    }

    public void setFarmacias(List<FarmaciaEntity> farmacias) {
        this.farmacias = farmacias;
    } 
    
}
