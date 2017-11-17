/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.LaboratorioEntity;

/**
 *
 * @author dm.gutierrez11
 */
public class LaboratorioDetailDTO extends LaboratorioDTO {
    
    public LaboratorioDetailDTO(){
        //inicialmente vacio
    }
    
    /**
     * Constructor para convertir un entity en DTO
     * @param laboratorio 
     */
    public LaboratorioDetailDTO (LaboratorioEntity laboratorio){
        super(laboratorio);
    }
    
    /**
     * Transforma un DTO a Entity
     * @return 
     */
    @Override
    public LaboratorioEntity toEntity() {
        return super.toEntity();
        
        
    }
}
