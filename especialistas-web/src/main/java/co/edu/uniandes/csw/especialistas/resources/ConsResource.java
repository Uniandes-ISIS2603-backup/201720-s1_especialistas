/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.ConsDetailDTO;
import co.edu.uniandes.csw.especialistas.dtos.FarmaciaDTO;
import co.edu.uniandes.csw.especialistas.dtos.HosDTO;
import co.edu.uniandes.csw.especialistas.dtos.MedicamentoDTO;
import co.edu.uniandes.csw.especialistas.dtos.MedicamentoDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.ConsLogic;
import co.edu.uniandes.csw.especialistas.ejb.Cons_HosLogic;
import co.edu.uniandes.csw.especialistas.ejb.MedicamentoLogic;
import co.edu.uniandes.csw.especialistas.ejb.Medicamento_FarmaciaLogic;
import co.edu.uniandes.csw.especialistas.entities.ConsEntity;
import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import javax.persistence.EntityManager;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rc.tejon
 */
@Path("cons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces("application/json")
@Stateless
public class ConsResource {

    /**
     * Clase de la lógica   
     */
    @Inject
    ConsLogic logic;
    

    @Inject
    Cons_HosLogic logicCH;
    

    /**
     * Recurso que crea un farmacia
     * @param consultorio JSON con la información del farmacia
     * @return Entidad del hospital creado
     */
    @POST
    public ConsDetailDTO createConsultorio(ConsDetailDTO consultorio)
    {
        ConsEntity entity = consultorio.toEntity();
        return new ConsDetailDTO(logic.createConsultorio(entity));
    }
    
    /**
     * Recurso que obtiene un farmacia por su id
     * @param id id del farmacia
     * @return FarmaciaEntity del hospital
     */
    @GET
    @Path("{id: \\d+}")
    public ConsDetailDTO getConsultorio(@PathParam("id") Long id)throws BusinessLogicException
    {
        ConsEntity entity = logic.getConsultorio(id);
        if(entity==null)
        {
            throw new BusinessLogicException("no existe la entidad con el id dado");
        }
        return new ConsDetailDTO(entity);
    }
    
    
    
    
    @GET
    @Path("{id: \\d+}/hospital")
    public HosDTO getHospitalConsultorio(@PathParam("id") Long id)throws WebApplicationException
    {
        ConsEntity entity = logic.getConsultorio(id);
        if(entity==null)
        {
            throw new WebApplicationException("no existe la entidad");
        }
        HosDTO hospital = new HosDTO(entity.getHospital());
        return hospital;
    }
    
    
    /**
     * Recurso que obtiene todos los hospitales
     * @return Lista con todos los hospitales
     */
    @GET
    public List<ConsDetailDTO> getConsultorios()
    {
        return listToList(logic.getConsultorios());
        
    }
    
    private List<ConsDetailDTO> listToList(List<ConsEntity> entityList) {
        List<ConsDetailDTO> listDTO=new ArrayList<>();
        for(ConsEntity entity: entityList) {
            listDTO.add(new ConsDetailDTO(entity));
        }
        return listDTO;
    }
    
    /**
     * Recurso para actualizar un farmacia
     * @return farmacia actualizado
     */
    @PUT
    public ConsDetailDTO updateConsultorio(ConsDetailDTO consultorio)throws BusinessLogicException
    {
        ConsEntity entity = consultorio.toEntity();
        if(logic.getConsultorio(entity.getId())==null)
        {
            throw new BusinessLogicException("ponga el id");

        }
        return new ConsDetailDTO(logic.updateConsultorio(entity));
    }
    
    /**
     * Recurso que elimina un farmacia
     * @param id id del farmacia
     */
    @Path("{id:\\d+}")
    @DELETE
    public void deleteConsultorio(@PathParam("id") Long id)throws BusinessLogicException
    {
         logic.deleteConsultorio(id);
    }
    
}
