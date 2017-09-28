/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author rc.tejon
 */
public class MedicamentoDetailDTO extends MedicamentoDTO{
    
    private List<FarmaciaDTO> farmacias;
    
    
    public MedicamentoEntity toEntity()
    {
        MedicamentoEntity entity = super.toEntity();
        List<FarmaciaEntity> listFarmacias=new ArrayList<FarmaciaEntity>();
        if(farmacias!=null)
        {
             Iterator<FarmaciaDTO> iter=farmacias.iterator();
             while(iter.hasNext())
             {
                listFarmacias.add(iter.next().toEntity());
             }
             entity.setFarmacias(listFarmacias);
        }
        return entity;
    }
    
}
