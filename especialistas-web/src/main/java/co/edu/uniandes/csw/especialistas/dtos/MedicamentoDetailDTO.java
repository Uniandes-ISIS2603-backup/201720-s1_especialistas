/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import java.util.List;

/**
 *
 * @author rc.tejon
 */
public class MedicamentoDetailDTO extends MedicamentoDTO{
    
    private List<FarmaciaDTO> farmacias;

    public MedicamentoDetailDTO(MedicamentoEntity entity) {
        super(entity);
        
        entity.getFarmacias().forEach((x)->{
            this.farmacias.add(new FarmaciaDTO(x));
    });
    }
    
    
    
    public MedicamentoEntity toEntity()
    {
        MedicamentoEntity entity = super.toEntity();
        farmacias.forEach((x) -> {
            entity.getFarmacias().add(x.toEntity());
        });
        return entity;
    }
    
}
