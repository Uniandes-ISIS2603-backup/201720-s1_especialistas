/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.OrdenMedicaDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.OrdenMedicaLogic;
import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;
import java.util.ArrayList;
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
import org.springframework.util.Assert;

/**
 *
 * @author jr.restom10
 */

@Path("ordenesMedicas")
@Produces("application/json")

public class OrdenMedicaResource {
    
    private final OrdenMedicaLogic logic;
     
     
     public OrdenMedicaResource(){
        logic = null;
    }
    
    @Inject
    public OrdenMedicaResource(OrdenMedicaLogic logic){
        Assert.notNull(logic, "MyCollaborator must not be null!");
        this.logic = logic;
    }
    
    /**
     * Recurso que crea un cita
     * @param cita JSON con la información del OrdenMedica
     * @return Entidad del OrdenMedica creado
     */
    @POST
    public OrdenMedicaDetailDTO createOrdenMedica(OrdenMedicaDetailDTO cita)
    {
        OrdenMedicaEntity entity = cita.toEntity();
        logic.createOrdenMedica(entity);
        return new OrdenMedicaDetailDTO(entity);
    }
    
    /**
     * Recurso que obtiene un OrdenMedica por su id
     * @param id id del OrdenMedica
     * @return OrdenMedicaEntity del OrdenMedica
     */
    @GET
    @Path("{id: \\d+}")
    public OrdenMedicaDetailDTO getOrdenMedica(@PathParam("id") Long id)
    {
        OrdenMedicaEntity entity = logic.getOrdenMedica(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /ordenesMedicas/" + id + " no existe.", 404);
        }

        return new OrdenMedicaDetailDTO(entity);
    }
    
    /**
     * Recurso que obtiene todos los OrdenMedicas
     * @return Lista con todos los OrdenMedicas
     */
    @GET
    public List<OrdenMedicaDetailDTO> getOrdenMedicas()
    {
        List<OrdenMedicaDetailDTO> lista = new ArrayList<>();
        logic.getOrdenesMedicas().forEach(ordenMedica -> 
            lista.add(new OrdenMedicaDetailDTO(ordenMedica))
        );
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
        return new OrdenMedicaDetailDTO(logic.updateOrdenMedica(entity));
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
