/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.TarjetaDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.TarjetaLogic;
import co.edu.uniandes.csw.especialistas.entities.TarjetaEntity;
import java.util.ArrayList;
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
import javax.ws.rs.WebApplicationException;
import org.springframework.util.Assert;

/**
 *
 * @author ce.quintero
 */
@Path("tarjetas")
@Produces("application/json")
@Stateless
public class TargetaResource {
    
    private final TarjetaLogic logic;
    
    public TargetaResource(){
        logic = null;
    }
    
    @Inject
    public TargetaResource(TarjetaLogic logic){
        Assert.notNull(logic, "MyCollaborator must not be null!");
        this.logic = logic;
    }
    
    @POST
    public TarjetaEntity createTarjeta(TarjetaDetailDTO tarjeta)  {
        TarjetaEntity tarjetaEntity = null;
        if(tarjeta.getId()!=null)
        {
            tarjetaEntity = logic.getTarjeta(tarjeta.getId());
        }
        if (tarjetaEntity != null) {
            throw new WebApplicationException("Ya existe un usuario con el id " + tarjeta.getId());
            
        }
        return logic.createTarjeta(tarjeta.toEntity());

        
    }
    
    @GET
    public List<TarjetaDetailDTO> getTarjetas(){
        List<TarjetaEntity> tarjetaEntities = logic.getTarjetas();
        if (tarjetaEntities.isEmpty()) {
            throw new WebApplicationException("no hay Tarjetas");
            
        }
        List<TarjetaDetailDTO> tarjetaDTOs = new ArrayList<>();

        for (TarjetaEntity actual : tarjetaEntities) {
            TarjetaDetailDTO nuevoTarjeta= new TarjetaDetailDTO(actual);
            tarjetaDTOs.add(nuevoTarjeta);
        }
        return tarjetaDTOs;
    }
    
    @GET
    @Path("{id: \\d+}")
    public TarjetaDetailDTO getTarjetaByID(@PathParam("id") Long id){
        TarjetaEntity entity = logic.getTarjeta(id);
        if (entity == null) {
            throw new WebApplicationException("1 No existe un Tarjeta con el id " + id);
            
        }
        return new TarjetaDetailDTO(entity);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public TarjetaDetailDTO updateTarjeta(@PathParam("id") Long id, TarjetaDetailDTO tarjeta){
        tarjeta.setId(id);
        TarjetaEntity entity = logic.getTarjeta(id);
        if (entity == null) {
            throw new WebApplicationException("2 No existe un Tarjeta con el id " + id);
            
        }
        return new TarjetaDetailDTO(logic.updateTarjeta(tarjeta.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTarjeta(@PathParam("id") Long id){
        TarjetaEntity entity = logic.getTarjeta(id);
        if (entity == null) {
            throw new WebApplicationException("3 No existe un Tarjeta con el id " + id);
            
        }
        logic.deleteTarjeta(id);
    }
}
