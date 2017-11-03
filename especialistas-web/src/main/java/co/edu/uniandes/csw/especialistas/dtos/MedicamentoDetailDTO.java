/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rc.tejon
 */
public class MedicamentoDetailDTO extends MedicamentoDTO{
    
    private List<FarmaciaDTO> farmacias;

    public MedicamentoDetailDTO() {
        //inicialmente vacio
    }

    
    public MedicamentoDetailDTO(MedicamentoEntity entity) {
        super(entity);
        if(entity.getFarmacias()==null)
        {
            entity.setFarmacias(new ArrayList<>());
            System.out.println("*********************************************************************************"+ entity.getFarmacias().toString());
        }
        entity.getFarmacias().forEach((x)->{
            this.farmacias.add(new FarmaciaDTO(x));
    });
    }
    
    
    
    public MedicamentoEntity toEntity()
    {
        MedicamentoEntity entity = super.toEntity();
        if(farmacias==null)
        {
                        farmacias= new ArrayList<>();
        }
        farmacias.forEach((x) -> {
            entity.getFarmacias().add(x.toEntity());
        });
        return entity;
    }
    
}
