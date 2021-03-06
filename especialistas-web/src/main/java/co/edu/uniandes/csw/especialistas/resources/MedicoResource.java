/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.HoraDTO;
import co.edu.uniandes.csw.especialistas.dtos.HoraDetailDTO;
import co.edu.uniandes.csw.especialistas.dtos.MedicoDTO;
import co.edu.uniandes.csw.especialistas.dtos.MedicoDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.MedicoLogic;
import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicoEntity;
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
 *
 * @author JuanSebastian
 */
@Path("medicos")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class MedicoResource {

   /**
     * Clase de la lógica
     */
    
    private final MedicoLogic logic;
    
     public MedicoResource(){
        logic = null;
    }
    
    @Inject
    public MedicoResource(MedicoLogic logic){
        Assert.notNull(logic, "logic must not be null!");
        this.logic = logic;
    }
    
    /**
     * Recurso que crea un medico
     * @param medico JSON con la información del medico
     * @return JSON con el medico creado
     */
    @POST
    public MedicoDetailDTO createMedico(MedicoDetailDTO medico)
    {
        MedicoEntity entity = medico.toEntity();
        logic.createMedico(entity);
        return new MedicoDetailDTO(entity);
    }
    
    /**
     * Recurso que obtiene un medico por su id
     * @param id id del medico
     * @return medico creado en formato JSON
     */
    @GET
    @Path("{id: \\d+}")
    public MedicoDetailDTO getMedico(@PathParam("id") Long id)
    {
        MedicoEntity entity = logic.getMedico(id);
        if (entity == null) {
            throw new WebApplicationException("1 El recurso /medicos/" + id + " 1 no existe.", 404);
        }

        return new MedicoDetailDTO(entity);
    }
    
    /**
     * Recurso que obtiene todos los medicos
     * @return Lista con todos los medicos
     */
    @GET
    public List<MedicoDetailDTO> getMedicos()
    {
        List<MedicoDetailDTO> lista = new ArrayList<>();
        logic.getMedicos().forEach(medico -> 
            lista.add(new MedicoDetailDTO(medico))
        );
        return lista;
    }
    
    /**
     * Recurso que obtiene todos los medicos de una especializacion dada
     * @param especializacion Especializacion a buscar
     * @return Lista con todos los medicos de la especializacion pedida
     */
    @GET
    @Path("especializacion/{especializacion}")
    public List<MedicoDetailDTO> getMedicosPorEspecializacion(@PathParam("especializacion") String especializacion)
    {
        List<MedicoDetailDTO> lista = new ArrayList<>();
        logic.getMedicosByEspecializacion(especializacion).forEach(medico -> 
            lista.add(new MedicoDetailDTO(medico))
        );
        return lista;
    }
    
    /**
     * Recurso para actualizar un medico
     * @param id Id del medico a actualizar
     * @param medico JSON con los detalles del medico
     * @return Medico actualizado
     */
    @PUT
    @Path("{id: \\d+}")
    public MedicoDTO updateMedico(@PathParam("id") Long id, MedicoDTO medico)
    {
        MedicoEntity newEntity = medico.toEntity();
        MedicoEntity entity = logic.getMedico(id);
        if (entity == null) {
            throw new WebApplicationException(" 2 El recurso /medicos/" + id + " 2 no existe.", 404);
        }
        newEntity.setAgenda(entity.getAgenda());
        return new MedicoDTO(logic.updateMedico(id, newEntity));
    }
    
    /**
     * Recurso que elimina un medico
     * @param id id del medico
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteMedico(@PathParam("id") Long id)
    {
        MedicoEntity entity = logic.getMedico(id);
        if (entity == null) {
            throw new WebApplicationException("3 El recurso /medicos/" + id + " 3 no existe.", 404);
        }
        logic.deleteMedico(id);
    }
    
    /**
     * Recurso que da la agenda de un médico
     * @param id id del médico
     * @return lista con las horas en representación JSON Detail
     */
    @GET
    @Path("{id: \\d+}/agenda")
    public List<HoraDetailDTO> getAgenda(@PathParam("id") Long id){
        MedicoEntity entity = logic.getMedico(id);
        if (entity == null) {
            throw new WebApplicationException("4 El recurso /medicos/" + id + " 4 no existe.", 404);
        }

        MedicoEntity newEntity = logic.getMedico(id);
        List<HoraDetailDTO> lista = new ArrayList<>();
        for(HoraEntity hora : newEntity.getAgenda()){
            lista.add(new HoraDetailDTO(hora));
        };
        return lista;
    }
    
    /**
     * Recurso que cambia la agenda de un médico
     * @param id id del médico
     * @param agenda agenda para reemplazar
     * @return agenda cambiada
     */
    @PUT
    @Path("{id: \\d+}/agenda")
    public List<HoraDetailDTO> cambiarAgenda(@PathParam("id") Long id, List<HoraDTO> agenda){
        MedicoEntity entity = logic.getMedico(id);
        if (entity == null) {
            throw new WebApplicationException("5 El recurso /medicos/" + id + " 5 no existe.", 404);
        }
        MedicoEntity newEntity = logic.getMedico(id);
        List<HoraEntity> lista = new ArrayList<>();
        agenda.forEach(hora -> 
            lista.add(hora.toEntity())
        );
        newEntity.setAgenda(lista);
        logic.updateMedico(id, newEntity);
        return getAgenda(id);
    }
}
