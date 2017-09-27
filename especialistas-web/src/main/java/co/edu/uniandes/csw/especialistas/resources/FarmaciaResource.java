/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.FarmaciaDTO;
import co.edu.uniandes.csw.especialistas.dtos.FarmaciaDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.FarmaciaLogic;
import javax.persistence.EntityManager;
import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import java.net.URI;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rc.tejon
 */
@Path("farmacias")
@Produces("application/json")
@Stateless
public class FarmaciaResource {

    public FarmaciaResource() {
    }

    /**
     * Clase de la lógica
     */
    @Inject
    FarmaciaLogic logic;
    
    /**
     * Recurso que crea un farmacia
     * @param farmacia JSON con la información del farmacia
     * @return Entidad del hospital creado
     */
    @POST
    public FarmaciaEntity createFarmacia(FarmaciaDTO farmacia)
    {
        FarmaciaEntity entity = farmacia.toEntity();
        logic.createFarmacia(entity);
        return entity;
    }
    
    /**
     * Recurso que obtiene un farmacia por su id
     * @param id id del farmacia
     * @return FarmaciaEntity del hospital
     */
    @GET
    @Path("{id: \\d+}")
    public FarmaciaEntity getFarmacia(@PathParam("id") Long id)
    {
        FarmaciaEntity entity = logic.getFarmacia(id);
        return entity;
    }
    
    /**
     * Recurso que obtiene todos los hospitales
     * @return Lista con todos los hospitales
     */
    @GET
    public List<FarmaciaEntity> getFarmacias()
    {
        List<FarmaciaEntity> lista = logic.getFarmacias();
        return lista;
    }
    
    /**
     * Recurso para actualizar un farmacia
     * @param farmacia JSON con los detalles del framacia
     * @return farmacia actualizado
     */
    @PUT
    public FarmaciaEntity updateFarmacia(FarmaciaDetailDTO farmacia)throws Exception
    {
        FarmaciaEntity entity = farmacia.toEntity();
        if(logic.getFarmacia(entity.getId())==null)
        {
            throw new Exception("ponga el id");
        }
        return logic.updateFarmacia(entity);
    }
    
    /**
     * Recurso que elimina un farmacia
     * @param id id del farmacia
     * @return true si se eliminó el farmacia, false de lo contrario
     */
    @Path("{id:\\d+}")
    @DELETE
    public boolean deleteFarmacia(@PathParam("id") Long id)
    {
        return logic.deleteFarmacia(id);
    }
    
}
