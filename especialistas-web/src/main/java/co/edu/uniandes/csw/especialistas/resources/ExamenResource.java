/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.ExamenDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.ExamenLogic;
import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
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
 *
 * @author dm.gutierrez11
 */
@Path("examenes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ExamenResource {
    
    private final ExamenLogic logic;
    
    public ExamenResource(){
        logic = null;
    }
    
    @Inject
    public ExamenResource(ExamenLogic logic){
        Assert.notNull(logic, "MyCollaborator must not be null!");
        this.logic = logic;
    }

    @POST
    public ExamenDetailDTO createExamen(ExamenDetailDTO examen){
        ExamenEntity examenEntity = logic.getExamen(examen.getNombre());
        if (examenEntity != null) {
            throw new WebApplicationException("Ya existe un examen con el nombre " + examen.getNombre());
             
        }
        ExamenEntity nuevoExamen = logic.createExamen(examen.toEntity());

        return new ExamenDetailDTO(nuevoExamen);
    }

    @GET
    public List<ExamenDetailDTO> getExamens(){
        List<ExamenEntity> examenEntities = logic.getExamenes();
        if (examenEntities.isEmpty()) {
            
            throw  new WebApplicationException("no hay examenes");
        }
        List<ExamenDetailDTO> examenDTOs = new ArrayList<>();

        for (ExamenEntity actual : examenEntities) {
            ExamenDetailDTO nuevoExamen = new ExamenDetailDTO(actual);
            examenDTOs.add(nuevoExamen);
        }
        return examenDTOs;
    }

    @GET
    @Path("{nombre}") 
    public ExamenDetailDTO getExamenByName(@PathParam("nombre") String nombre){
        ExamenEntity entity = logic.getExamen(nombre);
        if (entity == null) {
            throw new WebApplicationException("No existe un examen con el nombre " + nombre);
        }
        return new ExamenDetailDTO(entity);
    }

    @GET
    @Path("{id: \\d+}")
    public ExamenDetailDTO getExamenByID(@PathParam("id") Long id){
        ExamenEntity entity = logic.getExamenById(id);
        if (entity == null) {
            throw new WebApplicationException("No existe un examen con el id " + id);
        }
        return new ExamenDetailDTO(entity);
    }

    @PUT
    @Path("{id: \\d+}")
    public ExamenDetailDTO updateExamen(@PathParam("id") Long id, ExamenDetailDTO examen){
        examen.setId(id);
        ExamenEntity entity = logic.getExamenById(id);
        if (entity == null) {
            throw new WebApplicationException("No existe un examen con el id " + id);
            
        }
        return new ExamenDetailDTO(logic.updateExamen(examen.toEntity()));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteExamen(@PathParam("id") Long id){
        ExamenEntity entity = logic.getExamenById(id);
        if (entity == null) {
            throw new WebApplicationException("No existe un examen con el id " + id);
            
        }
        logic.deleteExamen(id);
    }
}
