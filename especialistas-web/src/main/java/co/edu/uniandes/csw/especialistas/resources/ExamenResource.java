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

/**
 *
 * @author dm.gutierrez11
 */
@Path("examenes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ExamenResource {
    @Inject
    ExamenLogic logic;

    @POST
    public ExamenDetailDTO createExamen(ExamenDetailDTO examen) throws WebApplicationException{
        ExamenEntity examenEntity = logic.getExamen(examen.getNombre());
        if (examenEntity != null) {
            WebApplicationException e = new WebApplicationException("Ya existe un examen con el nombre " + examen.getNombre());
            throw e;
        }
        ExamenEntity nuevoExamen = logic.createExamen(examenEntity);

        return new ExamenDetailDTO(nuevoExamen);
    }

    @GET
    public List<ExamenDetailDTO> getExamens() throws WebApplicationException {
        List<ExamenEntity> examenEntities = logic.getExamenes();
        if (examenEntities.isEmpty()) {
            WebApplicationException e = new WebApplicationException("no hay examenes");
            throw e;
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
    public ExamenDetailDTO getExamenByName(@PathParam("nombre") String nombre) throws WebApplicationException {
        ExamenEntity entity = logic.getExamen(nombre);
        if (entity == null) {
            WebApplicationException e = new WebApplicationException("No existe un examen con el nombre " + nombre);
            throw e;
        }
        return new ExamenDetailDTO(entity);
    }

    @GET
    @Path("{id: \\d+}")
    public ExamenDetailDTO getExamenByID(@PathParam("id") Long id) throws WebApplicationException {
        ExamenEntity entity = logic.getExamenById(id);
        if (entity == null) {
            WebApplicationException e = new WebApplicationException("No existe un examen con el id " + id);
            throw e;
        }
        return new ExamenDetailDTO(entity);
    }

    @PUT
    @Path("{id: \\d+}")
    public ExamenDetailDTO updateExamen(@PathParam("id") Long id, ExamenDetailDTO examen) throws WebApplicationException {
        examen.setId(id);
        ExamenEntity entity = logic.getExamenById(id);
        if (entity == null) {
            WebApplicationException e = new WebApplicationException("No existe un examen con el id " + id);
            throw e;
        }
        return new ExamenDetailDTO(logic.updateExamen(examen.toEntity()));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteExamen(@PathParam("id") Long id) throws WebApplicationException {
        ExamenEntity entity = logic.getExamenById(id);
        if (entity == null) {
            WebApplicationException e = new WebApplicationException("No existe un examen con el id " + id);
            throw e;
        }
        logic.deleteExamen(id);
    }
}
