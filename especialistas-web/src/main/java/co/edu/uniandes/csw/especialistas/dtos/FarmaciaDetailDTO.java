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
public class FarmaciaDetailDTO extends FarmaciaDTO{
    

    private List<MedicamentoDTO> medicamentos;

    public List<MedicamentoDTO> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<MedicamentoDTO> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public FarmaciaEntity toEntity()
    {
        FarmaciaEntity entity = super.toEntity();
        List<MedicamentoEntity> listMedicamentos=new ArrayList<MedicamentoEntity>();
        Iterator<MedicamentoDTO> iter=medicamentos.iterator();
        while(iter.hasNext())
        {
            listMedicamentos.add(iter.next().toEntity());
        }
        entity.setMedicamentos(listMedicamentos);
        
        return entity;
    }
}
