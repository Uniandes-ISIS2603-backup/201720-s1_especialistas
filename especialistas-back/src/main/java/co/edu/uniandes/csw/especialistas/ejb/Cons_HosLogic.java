/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.ConsEntity;
import co.edu.uniandes.csw.especialistas.entities.HosEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import javax.inject.Inject;
import org.springframework.util.Assert;

/**
 *
 * @author rc.tejon
 */
public class Cons_HosLogic {
    
    
    private final HosLogic logicHospital;
    
    
    private final ConsLogic logicConsultorio;
    
    public Cons_HosLogic(){
        logicHospital = null;
        logicConsultorio = null;
    }
    
    @Inject
    public Cons_HosLogic(HosLogic logicHospital, ConsLogic logicConsultorio){
        Assert.notNull(logicHospital, "MyCollaborator must not be null!");
        Assert.notNull(logicConsultorio, "MyCollaborator must not be null!");
        this.logicHospital = logicHospital;
        this.logicConsultorio = logicConsultorio;
    }
    
    public boolean agregarRelacion(Long idHospital, Long idConsultorio)
    {
        ConsEntity entityM = logicConsultorio.getConsultorio(idConsultorio);
        HosEntity entityF= logicHospital.getHospital(idHospital);
        if(entityF==null||entityM==null)
        {
            
            return false;
        }
        return true;
    }
    
        public boolean eliminarRelacion(Long idHospital, Long idConsultorio)throws BusinessLogicException
    {
        ConsEntity entityM = logicConsultorio.getConsultorio(idConsultorio);
        HosEntity entityF= logicHospital.getHospital(idHospital);
        if(entityF==null||entityM==null)
        {
            return false;
        }
        return true;
    }
    
    
}
