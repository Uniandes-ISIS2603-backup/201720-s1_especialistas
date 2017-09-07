/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author jl.patarroyo
 */
@MappedSuperclass
public abstract class BaseEntity 
{
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
