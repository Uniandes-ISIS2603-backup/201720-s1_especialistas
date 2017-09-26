/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.ConsultorioDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.ConsultorioLogic;
import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
    
    /**
     * Método encargado de listar los consultorios
     * @return Lista de consultorios
     */
    @GET
    public List<ConsultorioEntity> getConsultorios()
    {
        return logic.getConsultorios();
    }
    
    /**
     * Método encargado de obtener un consultorio por id
     * @param id id del consultorio
     * @return Consultorio buscado
     */
    @GET
    @Path("{id: \\d+}")
    public ConsultorioEntity getConsultorio(@PathParam("id") Long id)
    {
        return logic.getConsultorio(id);
    }
    
    /**
     * Método encargado de crear un consultorio
     * @param entity entidad con la información
     * @return entidad creada
     */
    @POST
    public ConsultorioEntity createConsultorio(ConsultorioEntity entity)
    {
        return logic.createConsultorio(entity);
    }
    
    /**
     * Método encargado de actualizar la información de un consultorio
     * @param dto ConsultorioDetailDTO con la información
     * @return ConsultorioEntity con la información actualizada
     */
    @PUT
    public ConsultorioEntity upadeConsultorio(ConsultorioDetailDTO dto)
    {
        ConsultorioEntity entity = dto.toEntity();
        return logic.updateConsultorio(entity);
    }
    
}
