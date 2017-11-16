/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.ConsultorioDTO;
import co.edu.uniandes.csw.especialistas.dtos.ConsultorioDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.ConsultorioLogic;
import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.transaction.annotation.Transactional;

/**
 * Atributo que modela el recurso de un consultorio
 *
 * @author jl.patarroyo
 */
@Path("consultorios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces("application/json")
@Stateless
public class ConsultorioResource {

    /**
     * Atributo que modela la lógica de los consultorios
     */
    
    private final ConsultorioLogic logic;
    
    public ConsultorioResource(){
        logic = null;
    }
    
    @Inject
    ConsultorioLogic logic;

    /**
     * Método que busca todos los consultorios
     *
     * @return lista con todos los consultorios
     * @throws BusinessLogicException si no existen consultorios
     */
    @GET
    public List<ConsultorioDTO> getConsultorios() throws BusinessLogicException {
        List<ConsultorioDTO> lista = new ArrayList<>();
        List<ConsultorioEntity> consultorios = logic.getConsultorios();
        for (ConsultorioEntity entidad : consultorios) {
            ConsultorioDTO consultorio = new ConsultorioDTO(entidad);
            lista.add(consultorio);
        }
        return lista;
    }

    @GET
    @Path("/{id: \\d+}")
    public ConsultorioDetailDTO getConsultorio(@PathParam("id") Long idConsultorio) {
        try {
            ConsultorioEntity entidad = logic.getConsultorio(idConsultorio);
            ConsultorioDetailDTO consultorio = new ConsultorioDetailDTO(entidad);
            return consultorio;
        } catch (BusinessLogicException e) {
            throw new WebApplicationException(e.getMessage(), 404);
        }
    }

}
