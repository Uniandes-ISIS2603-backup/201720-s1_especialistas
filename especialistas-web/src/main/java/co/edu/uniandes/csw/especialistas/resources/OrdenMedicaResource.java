/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.OrdenMedicaDTO;
import co.edu.uniandes.csw.especialistas.ejb.OrdenMedicaLogic;
import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jr.restom10
 */

@Path("ordenesMedicas")
@Produces("application/json")

public class OrdenMedicaResource {
     @Inject 
    OrdenMedicaLogic logic;
    
    /**
     * Recurso que crea un ordenMedica
     * @param ordenMedica JSON con la información del OrdenMedica
     * @return Entidad del OrdenMedica creado
     */
    @POST
    public OrdenMedicaEntity createOrdenMedica(OrdenMedicaDTO ordenMedica)
    {
        OrdenMedicaEntity entity = ordenMedica.toEntity();
        logic.createOrdenMedica(entity);
        return entity;
    }
    
    /**
     * Recurso que obtiene un OrdenMedica por su id
     * @param id id del OrdenMedica
     * @return OrdenMedicaEntity del OrdenMedica
     */
    @GET
    @Path("{id: \\d+}")
    public OrdenMedicaEntity getOrdenMedica(@PathParam("id") Long id)
    {
        OrdenMedicaEntity entity = logic.getOrdenMedica(id);
        return entity;
    }
    
    /**
     * Recurso que obtiene todos los OrdenMedicas
     * @return Lista con todos los OrdenMedicas
     */
    @GET
    public List<OrdenMedicaEntity> getOrdenMedicas()
    {
        List<OrdenMedicaEntity> lista = logic.getOrdenesMedicas();
        return lista;
    }
    
    /**
     * Recurso para actualizar un OrdenMedica
     * @param ordenMedica JSON con los detalles del OrdenMedica
     * @return OrdenMedica actualizado
     */
    @PUT
    public OrdenMedicaEntity updateOrdenMedica(OrdenMedicaDTO ordenMedica)
    {
        OrdenMedicaEntity entity = ordenMedica.toEntity();
        return logic.updateOrdenMedica(entity);
    }
    
    /**
     * Recurso que elimina un OrdenMedica
     * @param id id del OrdenMedica
     * @return true si se eliminó el OrdenMedica, false de lo contrario
     */
    @DELETE
    public boolean deleteOrdenMedica(@PathParam("id") Long id)
    {
        return logic.deleteOrdenMedica(id);
    }
    
    
    
}
