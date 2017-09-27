/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.MedicamentoDTO;
import co.edu.uniandes.csw.especialistas.dtos.MedicamentoDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.MedicamentoLogic;
import javax.persistence.EntityManager;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
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
@Path("medicamentos")
@Produces("application/json")
@Stateless
public class MedicamentoResourse {


    public MedicamentoResourse() {
    }

    /**
     * Clase de la lógica
     */
    @Inject
    MedicamentoLogic logic;
    
    /**
     * Recurso que crea un medicamento
     * @param medicamento JSON con la información del medicamento
     * @return Entidad del medicamento creado
     */
    @POST
    public MedicamentoEntity createMedicamento(MedicamentoDTO medicamento)
    {
        MedicamentoEntity entity = medicamento.toEntity();
        logic.createMedicamento(entity);
        return entity;
    }
    
    /**
     * Recurso que obtiene un medicamento por su id
     * @param id id del medicamento
     * @return MedicamentoEntity del medicamento
     */
    @GET
    @Path("{id: \\d+}")
    public MedicamentoEntity getMedicamento(@PathParam("id") Long id)
    {
        MedicamentoEntity entity = logic.getMedicamento(id);
        return entity;
    }
    
    /**
     * Recurso que obtiene todos los medicamentos
     * @return Lista con todos los medicamentos
     */
    @GET
    public List<MedicamentoEntity> getMedicamentos()
    {
        List<MedicamentoEntity> lista = logic.getMedicamentos();
        return lista;
    }
    
    /**
     * Recurso para actualizar un medicamento
     * @param medicamento JSON con los detalles del medicamento
     * @return medicamento actualizado
     */
    @PUT
    public MedicamentoEntity updateMedicamento(MedicamentoDetailDTO medicamento)throws Exception
    {
        MedicamentoEntity entity = medicamento.toEntity();
        if(logic.getMedicamento(entity.getId())==null)
        {
            throw new Exception("ponga el id");
        }
        return logic.updateMedicamento(entity);
    }
    
    /**
     * Recurso que elimina un medicamento
     * @param id id del medicamento
     * @return true si se eliminó el medicamento, false de lo contrario
     */
    @Path("{id:\\d+}")
    @DELETE
    public boolean deleteFarmacia(@PathParam("id") Long id)
    {
        return logic.deleteMedicamento(id);
    }
}
