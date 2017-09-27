/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;


import co.edu.uniandes.csw.especialistas.entities.Especializacion;
import co.edu.uniandes.csw.especialistas.entities.MedicoEntity;

/**
 *
 * @author JuanSebastian
 */
public class MedicoDTO {
    
    private Long id;
    private String nombre;
    private String especializacion;

    public MedicoDTO() {
    }

    public MedicoDTO(MedicoEntity entity) {
        this.id = entity.getId();
        this.nombre = entity.getNombre();
        this.especializacion = entity.getEspecializacion().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }
    
    public MedicoEntity toEntity(){
        MedicoEntity entity = new MedicoEntity();
        entity.setId(id);
        entity.setNombre(nombre);
        entity.setEspecializacion(Enum.valueOf(Especializacion.class, especializacion));
        return entity;
    }
}    

