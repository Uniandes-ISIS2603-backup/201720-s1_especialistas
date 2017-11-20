/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import co.edu.uniandes.csw.especialistas.ejb.CitaLogic;
import co.edu.uniandes.csw.especialistas.entities.CitaEntity;
import co.edu.uniandes.csw.especialistas.dtos.CitaDetailDTO;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;
import org.springframework.util.Assert;


/**
 *
 * @author jr.restom10
 */

@Path("citas")
@Produces("application/json")

public class CitaResource {
    
    
    
    private final CitaLogic logic;
    
    
    public CitaResource(){
        logic = null;
    }
    
    @Inject
    public CitaResource(CitaLogic logic){
        Assert.notNull(logic, "logic must not be null!");
        this.logic = logic;
    }
    
    
    /**
     * Recurso que crea un cita
     * @param cita JSON con la información del Cita
     * @return Entidad del Cita creado
     */
    @POST
    public CitaDetailDTO createCita(CitaDetailDTO cita)
    {
        CitaEntity entity = cita.toEntity();
        logic.createCita(entity);
        return new CitaDetailDTO(entity);
    }
    
    /**
     * Recurso que obtiene un Cita por su id
     * @param id id del Cita
     * @return CitaEntity del Cita
     */
    @GET
    @Path("{id: \\d+}")
    public CitaDetailDTO getCita(@PathParam("id") Long id)
    {
        CitaEntity entity = logic.getCita(id);
        if (entity == null) {
            throw new WebApplicationException("1 El recurso /citas/" + id + " no existe!", 404);
        }

        return new CitaDetailDTO(entity);
    }
    
    /**
     * Recurso que obtiene todos los Citas
     * @return Lista con todos los Citas
     */
    @GET
    public List<CitaDetailDTO> getCitas()
    {
        List<CitaDetailDTO> lista = new ArrayList<>();
        logic.getCitas().forEach(cita -> 
            lista.add(new CitaDetailDTO(cita))
        );
        return lista;
    }
    
    /**
     * Recurso para actualizar un Cita
     * @param cita JSON con los detalles del Cita
     * @return Cita actualizado
     */
    @PUT
    public CitaDetailDTO updateCita(CitaDetailDTO cita)
    {
        CitaEntity newEntity = cita.toEntity();
        CitaEntity e = logic.getCita(newEntity.getId());
         if (e == null) {
            throw new WebApplicationException("2 El recurso /citas/" + cita.getId() + " no existe!!", 404);
        }
        return new CitaDetailDTO(logic.updateCita(newEntity));
    }
    
    /**
     * Recurso que elimina un Cita
     * @param id id del Cita
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCita(@PathParam("id") Long id)
    {
        CitaEntity entity = logic.getCita(id);
        if (entity == null) {
            throw new WebApplicationException("3 El recurso /citas/" + id + " no existe!!!", 404);
        }
        logic.deleteCita(id);
    }
    
    
    
}
