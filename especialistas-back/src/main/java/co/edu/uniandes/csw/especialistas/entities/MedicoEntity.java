/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author js.diaz
 */
@Entity
public class MedicoEntity extends BaseEntity {
    
    private String nombre;
    
    private Especializacion especializacion;
    
    @PodamExclude
    @OneToMany(mappedBy="medico")
    private List<HoraEntity> agenda;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Especializacion getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(Especializacion especializacion) {
        this.especializacion = especializacion;
    }

    public List<HoraEntity> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<HoraEntity> agenda) {
        this.agenda = agenda;
    }
}
