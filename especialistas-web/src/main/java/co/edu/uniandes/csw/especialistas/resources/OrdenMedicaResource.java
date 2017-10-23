/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.OrdenMedicaDTO;
import co.edu.uniandes.csw.especialistas.dtos.OrdenMedicaDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.OrdenMedicaLogic;
import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

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
         if (entity == null) {
            throw new WebApplicationException("El recurso /ordenesMedicas/" + id + " no existe.", 404);
        }
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
     * @param id
     * @param ordenMedica JSON con los detalles del OrdenMedica
     * @return OrdenMedica actualizado
     */
    @PUT
    @Path("{id: \\d+}")
    public OrdenMedicaDetailDTO updateOrdenMedica(@PathParam("id") Long id,OrdenMedicaDetailDTO ordenMedica)
    {
        OrdenMedicaEntity entity = ordenMedica.toEntity();
        OrdenMedicaEntity e = logic.getOrdenMedica(id);
         if (e == null) {
            throw new WebApplicationException("El recurso /citas/" + id + " no existe.", 404);
        }
        return new OrdenMedicaDetailDTO(logic.updateOrdenMedica(id,entity));
    }
    
    /**
     * Recurso que elimina un OrdenMedica
     * @param id id del OrdenMedica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteOrdenMedica(@PathParam("id") Long id)
    {
        
        OrdenMedicaEntity entity = logic.getOrdenMedica(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /ordenesMedicas/" + id + " no existe.", 404);
        }
        logic.deleteOrdenMedica(id);
    }
    
    
    
}
