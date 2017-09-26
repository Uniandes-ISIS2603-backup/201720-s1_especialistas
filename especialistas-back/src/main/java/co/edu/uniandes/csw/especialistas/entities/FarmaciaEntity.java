/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author rc.tejon
 */
@Entity
@XmlRootElement
public class FarmaciaEntity extends BaseEntity{
    
    private int radio;
    private String nombre;
    @OneToOne
    @JoinColumn(name="ubicadionID")
    private UbicacionEntity ubicacion;
    
    @PodamExclude
    @ManyToMany(mappedBy="farmacias")
    private List<MedicamentoEntity> medicamentos;
    public int getRadio() {
        return radio;
    }

    public void setRadio(int precio) {
        this.radio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<MedicamentoEntity> getMedicamentos() {
        return medicamentos;
    }

    public UbicacionEntity getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionEntity ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public void agregarMedicamento(MedicamentoEntity med)
    {
        medicamentos.add(med);
    }

    public void setMedicamentos(List<MedicamentoEntity> medicamentos) {
        this.medicamentos = medicamentos;
    }
    
}
