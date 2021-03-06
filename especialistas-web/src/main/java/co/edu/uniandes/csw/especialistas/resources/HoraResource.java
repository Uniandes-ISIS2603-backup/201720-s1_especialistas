/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.HoraDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.HoraLogic;
import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
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
import org.springframework.util.Assert;

/**
 * @author JuanSebastian
 */
@Path("horas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class HoraResource {

   /**
     * Clase de la lógica
     */
    
    private final HoraLogic logic;
    
    public HoraResource(){
        logic = null;
    }
    
    @Inject
    public HoraResource(HoraLogic logic){
        Assert.notNull(logic, "MyCollaborator must not be null!");
        this.logic = logic;
    }

    
    /**
     * Recurso que crea una hora
     * @param hora JSON con la información de la hora
     * @return JSON con la hora creada
     * @throws co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException
     */
    @POST
    public HoraDetailDTO createHora(HoraDetailDTO hora) throws BusinessLogicException
    {
        HoraEntity entity = hora.toEntity();
        logic.createHora(entity);
        return new HoraDetailDTO(entity);
    }
    
    /**
     * Recurso que obtiene una hora por su id
     * @param id id de la hora
     * @return hora creada en formato JSON
     */
    @GET
    @Path("{id: \\d+}")
    public HoraDetailDTO getHora(@PathParam("id") Long id)
    {
        HoraEntity entity = logic.getHora(id);
        if (entity == null) {
            throw new WebApplicationException("1 El recurso /horas/" + id + " 1 no existe.", 404);
        }
        return new HoraDetailDTO(entity);
    }
    
    /**
     * Recurso que obtiene todas las horas
     * @return Lista con todas las horas
     */
    @GET
    public List<HoraDetailDTO> getHoras()
    {
        List<HoraDetailDTO> lista = new ArrayList<>();
        logic.getHoras().forEach(hora -> 
            lista.add(new HoraDetailDTO(hora))
        );
        return lista;
    }
    
    /**
     * Recurso para actualizar una hora
     * @param id Id de la hora a actualizar
     * @param hora JSON con los detalles de la hora
     * @return Hora actualizada
     */
    @PUT
    @Path("{id: \\d+}")
    public HoraDetailDTO updateHora(@PathParam("id") Long id, HoraDetailDTO hora)
    {
        HoraEntity newEntity = hora.toEntity();
        HoraEntity entity = logic.getHora(id);
        if (entity == null) {
            throw new WebApplicationException("2 El recurso /horas/" + id + " 2 no existe.", 404);
        }
        return new HoraDetailDTO(logic.updateHora(id, newEntity));
    }
    
    /**
     * Recurso que elimina una hora
     * @param id id de la hora
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteHora(@PathParam("id") Long id)
    {
        HoraEntity entity = logic.getHora(id);
        if (entity == null) {
            throw new WebApplicationException("3 El recurso /horas/" + id + " 3 no existe.", 404);
        }
        logic.deleteHora(id);
    }
}
