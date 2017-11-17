/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author rc.tejon
 */
public class FarmaciaDetailDTO extends FarmaciaDTO{

    public FarmaciaDetailDTO() {
        //inicialmente vacio
    }

    
    public FarmaciaDetailDTO(FarmaciaEntity entity) {
        super(entity);
        entity.getMedicamentos().forEach(x -> 
            this.medicamentos.add(new MedicamentoDTO(x))
        );
    }
    private List<MedicamentoDTO> medicamentos;

    public List<MedicamentoDTO> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<MedicamentoDTO> medicamentos) {
        this.medicamentos = medicamentos;
    }

    @Override
    public FarmaciaEntity toEntity()
    {
        FarmaciaEntity entity = super.toEntity();
        if(medicamentos==null)
        {
            medicamentos=new ArrayList<>();
        }
        medicamentos.forEach((x) -> {
            entity.getMedicamentos().add(x.toEntity());
        });
        
        return entity;
    }
}
