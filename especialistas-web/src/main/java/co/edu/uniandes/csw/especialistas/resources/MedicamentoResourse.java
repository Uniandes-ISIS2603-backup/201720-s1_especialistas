/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.FarmaciaDTO;
import co.edu.uniandes.csw.especialistas.dtos.MedicamentoDTO;
import co.edu.uniandes.csw.especialistas.dtos.MedicamentoDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.MedicamentoLogic;
import co.edu.uniandes.csw.especialistas.ejb.Medicamento_FarmaciaLogic;
import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import javax.persistence.EntityManager;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces("application/json")
@Stateless
public class MedicamentoResourse {

    /**
     * Clase de la lógica   
     */
    @Inject
    MedicamentoLogic logic;
    

    @Inject
    Medicamento_FarmaciaLogic logicMF;
    

    /**
     * Recurso que crea un farmacia
     * @param farmacia JSON con la información del farmacia
     * @return Entidad del hospital creado
     */
    @POST
    public MedicamentoDetailDTO createMedicamento(MedicamentoDetailDTO farmacia)
    {
        MedicamentoEntity entity = farmacia.toEntity();
        return new MedicamentoDetailDTO(logic.createMedicamento(entity));
    }
    
    /**
     * Recurso que obtiene un farmacia por su id
     * @param id id del farmacia
     * @return FarmaciaEntity del hospital
     */
    @GET
    @Path("{id: \\d+}")
    public MedicamentoDetailDTO getFarmacia(@PathParam("id") Long id)throws BusinessLogicException
    {
        MedicamentoEntity entity = logic.getMedicamento(id);
        if(entity==null)
        {
            throw new BusinessLogicException("no existe la entidad con el id dado");
        }
        return new MedicamentoDetailDTO(entity);
    }
    
    
    
    
    @GET
    @Path("{id: \\d+}/farmacias")
    public List<FarmaciaDTO> getMedicamentoFarmacia(@PathParam("id") Long id)throws Exception
    {
        MedicamentoEntity entity = logic.getMedicamento(id);
        if(entity==null)
        {
            throw new Exception("no existe la entidad");
        }
        List<FarmaciaDTO> list= new ArrayList<>();
        Iterator<FarmaciaEntity> iter=entity.getFarmacias().iterator();
        while(iter.hasNext())
        {
            list.add(new FarmaciaDTO(iter.next()));
        }
        return list;
    }
    
    
    /**
     * Recurso que obtiene todos los hospitales
     * @return Lista con todos los hospitales
     */
    @GET
    public List<MedicamentoDetailDTO> getFarmacias()
    {
        return listToList(logic.getMedicamentos());
        
    }
    
    private List<MedicamentoDetailDTO> listToList(List<MedicamentoEntity> entityList) {
        List<MedicamentoDetailDTO> listDTO=new ArrayList<>();
        System.out.println("co.edu.uniandes.csw.especialistas.resources.MedicamentoResourse.listToList()");
        for(MedicamentoEntity entity: entityList) {
            listDTO.add(new MedicamentoDetailDTO(entity));
        }
        return listDTO;
    }
    
    /**
     * Recurso para actualizar un farmacia
     * @return farmacia actualizado
     */
    @PUT
    public MedicamentoDetailDTO updateFarmacia(MedicamentoDetailDTO medicamento)throws BusinessLogicException
    {
        MedicamentoEntity entity = medicamento.toEntity();
        if(logic.getMedicamento(entity.getId())==null)
        {
            throw new BusinessLogicException("ponga el id");
        }
        return new MedicamentoDetailDTO(logic.updateMedicamento(entity));
    }
    
    /**
     * Recurso que elimina un farmacia
     * @param id id del farmacia
     */
    @Path("{id:\\d+}")
    @DELETE
    public void deleteFarmacia(@PathParam("id") Long id)throws BusinessLogicException
    {
         logic.deleteMedicamento(id);
    }
    
}
