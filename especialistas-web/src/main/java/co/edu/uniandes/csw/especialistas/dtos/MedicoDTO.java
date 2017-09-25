/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.MedicoEntity;

/**
 *
 * @author JuanSebastian
 */
public class MedicoDTO {
    
    private Long id;
    private String nombre;

    public MedicoDTO() {
    }

    public MedicoDTO(MedicoEntity entity) {
        this.id = entity.getId();
        this.nombre = entity.getNombre();
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
    
    public MedicoEntity toEntity(){
        MedicoEntity entity = new MedicoEntity();
        entity.setId(id);
        entity.setNombre(nombre);
        return entity;
    }
}    
    
