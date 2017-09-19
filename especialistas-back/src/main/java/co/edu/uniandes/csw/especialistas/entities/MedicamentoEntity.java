/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
    @ManyToMany(mappedBy="medicamentos")
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
    
    public void agregarMedicamento(FarmaciaEntity farmacia)
    {
        farmacias.add(farmacia);
    }

    public List<FarmaciaEntity> getFarmacias() {
        return farmacias;
    }
    
    
    
}
