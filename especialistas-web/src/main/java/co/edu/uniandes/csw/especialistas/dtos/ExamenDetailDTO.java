/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;

/**
 *
 * @author dm.gutierrez11
 */
public class ExamenDetailDTO extends ExamenDTO {
    
    public ExamenDetailDTO(){
        //inicialmente vacio
    }
    
    /**
     * Constructor para convertir un entity en DTO
     * @param examen 
     */
    public ExamenDetailDTO (ExamenEntity examen){
        super(examen);
    }
    
    /**
     * Transforma un DTO a Entity
     * @return 
     */
    @Override
    public ExamenEntity toEntity() {
        return super.toEntity();
        
    }
    
}
