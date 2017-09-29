/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author rc.tejon
 */
@Entity
public class FarmaciaEntity extends BaseEntity{
    
    private int radio;
    private String nombre;
    @OneToOne(cascade = CascadeType.ALL)
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

    public List<MedicamentoEntity> getMedicamentos() {
        if(medicamentos==null)
        {
            medicamentos=new ArrayList<>();
        }
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

        if(medicamentos==null)
        {
            medicamentos= new ArrayList<>();
        }
        medicamentos.add(med);
    }
    
    public void eliminarMedicamento(MedicamentoEntity med)throws BusinessLogicException
    {

        if(medicamentos==null || !medicamentos.contains(med))
        {
            throw new BusinessLogicException("no existe el medicamento");
        }
        medicamentos.remove(med);
    }

    public void setMedicamentos(List<MedicamentoEntity> medicamentos) {
        this.medicamentos = medicamentos;
    }
    
}
