/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.ConsDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.ConsLogic;
import co.edu.uniandes.csw.especialistas.ejb.Cons_HosLogic;
import co.edu.uniandes.csw.especialistas.entities.ConsEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
