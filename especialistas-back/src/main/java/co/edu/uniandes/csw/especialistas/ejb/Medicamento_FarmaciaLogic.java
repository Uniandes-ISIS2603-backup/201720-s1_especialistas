/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import exceptions.BusinessLogicException;
import javax.inject.Inject;

/**
 *
 * @author rc.tejon
 */
public class Medicamento_FarmaciaLogic {
    
    @Inject
    private FarmaciaLogic logicFarmacia;
    
    @Inject
    private MedicamentoLogic logicMedicamento;
    
    public boolean agregarRelacion(Long idFarmacia, Long idMedicamento)
    {
        MedicamentoEntity entityM = logicMedicamento.getMedicamento(idMedicamento);
        FarmaciaEntity entityF= logicFarmacia.getFarmacia(idFarmacia);
        if(entityF==null||entityM==null)
        {
            return false;
        }
        entityF.agregarMedicamento(entityM);
        entityM.agregarFarmacia(entityF);
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
        entityM.eliminarFarmcia(entityF);
        return true;
    }
    
    
}
