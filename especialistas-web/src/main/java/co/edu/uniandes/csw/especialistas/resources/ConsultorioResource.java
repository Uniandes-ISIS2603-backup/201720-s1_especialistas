/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.ConsultorioDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.ConsultorioLogic;
import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
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
import javax.ws.rs.WebApplicationException;

/**
 * Recurso del consultorio
 * @author jl.patarroyo
 */

@Path("/consultorios")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ConsultorioResource {
    
    /**
     * Injección de la lógica
     */
    @Inject
    ConsultorioLogic logic;
    
    private List<ConsultorioDetailDTO> entitiesList2DTO(List<ConsultorioEntity> listaEntities){
        List<ConsultorioDetailDTO> list = new ArrayList<>();
        for(ConsultorioEntity consultorio: listaEntities){
            ConsultorioDetailDTO dto = new ConsultorioDetailDTO(consultorio);
            list.add(dto);
        }
        return list;
    }
    
    /**
     * Método encargado de listar los consultorios
     * @return Lista de consultorios
     */
    @GET
    public List<ConsultorioDetailDTO> getConsultorios()
    {
        return entitiesList2DTO(logic.getConsultorios());
    }
    
    /**
     * Método encargado de obtener un consultorio por id
     * @param id id del consultorio
     * @return Consultorio buscado
     */
    @GET
    @Path("{id: \\d+}")
    public ConsultorioDetailDTO getConsultorio(@PathParam("id") Long id)
    {
        ConsultorioEntity consultorio = logic.getConsultorio(id);
        if(consultorio == null){
            throw new WebApplicationException("El elemento no existe", 404);
        }
        return new ConsultorioDetailDTO(logic.getConsultorio(id));
    }
    
    /**
     * Método encargado de crear un consultorio
     * @param entity entidad con la información
     * @return entidad creada
     * @throws co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException
     */
    @POST
    public ConsultorioDetailDTO createConsultorio(ConsultorioDetailDTO entity) throws BusinessLogicException
    {
        return new ConsultorioDetailDTO(logic.createConsultorio(entity.toEntity()));
    }
    
    /**
     * Método encargado de actualizar la información de un consultorio
     * @param dto ConsultorioDetailDTO con la información
     * @return ConsultorioEntity con la información actualizada
     */
    @PUT
    public ConsultorioDetailDTO upadeConsultorio(ConsultorioDetailDTO dto)
    {
        ConsultorioEntity newEntity = dto.toEntity();
        ConsultorioEntity entity = logic.getConsultorio(newEntity.getId());
        if(entity == null){
            throw new WebApplicationException("El elemento no existe", 404);
        }
        return new ConsultorioDetailDTO(logic.updateConsultorio(newEntity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteConsultorio(@PathParam("id") Long id){
        ConsultorioEntity entity = logic.getConsultorio(id);
        if(entity == null){
            throw new WebApplicationException("El elemento no existe", 404);
        }
        logic.deleteConsultorioEntity(id);
    }
    
}
