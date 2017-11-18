/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import javax.inject.Inject;
import org.springframework.util.Assert;

/**
 *
 * @author rc.tejon
 */
public class Medicamento_FarmaciaLogic {
    
    
    private final FarmaciaLogic logicFarmacia;
    
    
    private final MedicamentoLogic logicMedicamento;
    
    public Medicamento_FarmaciaLogic(){
        logicFarmacia = null;
        logicMedicamento = null;
    }
    
    @Inject
    public Medicamento_FarmaciaLogic(FarmaciaLogic logicFarmacia, MedicamentoLogic logicMedicamento){
        Assert.notNull(logicFarmacia, "MyCollaborator must not be null!");
        Assert.notNull(logicMedicamento, "MyCollaborator must not be null!");
        this.logicFarmacia = logicFarmacia;
        this.logicMedicamento = logicMedicamento;
    }
    
    public boolean agregarRelacion(Long idFarmacia, Long idMedicamento)
    {
        MedicamentoEntity entityM = logicMedicamento.getMedicamento(idMedicamento);
        FarmaciaEntity entityF= logicFarmacia.getFarmacia(idFarmacia);
        if(entityF==null||entityM==null)
        {
            return false;
        }
        entityF.agregarMedicamento(entityM);
        logicFarmacia.updateFarmacia(entityF);
        entityM.agregarFarmacia(entityF);
        logicMedicamento.updateMedicamento(entityM);
        return true;
    }
    
        public boolean eliminarRelacion(Long idFarmacia, Long idMedicamento)throws BusinessLogicException
    {
        MedicamentoEntity entityM = logicMedicamento.getMedicamento(idMedicamento);
        FarmaciaEntity entityF= logicFarmacia.getFarmacia(idFarmacia);
        if(entityF==null||entityM==null)
        {
            return false;
        }
        entityF.eliminarMedicamento(entityM);
        logicMedicamento.updateMedicamento(entityM);
        entityM.eliminarFarmcia(entityF);
        logicFarmacia.updateFarmacia(entityF);
        return true;
    }
    
    
}
