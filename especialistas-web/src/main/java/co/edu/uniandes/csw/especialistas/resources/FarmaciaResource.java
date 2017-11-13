/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.FarmaciaDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.MedicamentoLogic;
import co.edu.uniandes.csw.especialistas.dtos.MedicamentoDTO;
import co.edu.uniandes.csw.especialistas.dtos.UbicacionDTO;
import co.edu.uniandes.csw.especialistas.ejb.FarmaciaLogic;
import co.edu.uniandes.csw.especialistas.ejb.Medicamento_FarmaciaLogic;
import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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


/**
 *
 * @author rc.tejon
 */
@Path("/farmacias")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class FarmaciaResource {

    /**
     * Clase de la lógica de farmacia  
     */
    @Inject
    FarmaciaLogic logic;
    
    @Inject
    MedicamentoLogic logicMedicamento;
    
    /**
     * Clase de la logica de los metodos que comparten medicamento y faracia
     */
    @Inject
    Medicamento_FarmaciaLogic logicMF;
    

    /**
     * Recurso que crea una farmacia
     * @param farmacia JSON con la información de la farmacia
     * @return Entidad de la farmacia creada
     */
    @POST
    public FarmaciaDetailDTO createFarmacia(FarmaciaDetailDTO farmacia)
    {
        FarmaciaEntity entity = farmacia.toEntity();
        return new FarmaciaDetailDTO(logic.createFarmacia(entity));
    }
    
    /**
     * Recurso que obtiene una farmacia por su id
     * @param id id del farmacia buscada
     * @return FarmaciaDetailDTO con la informacion de la farmacia
     * @throws BusinessLogicException si no existe la farmacia con el id dado
     */
    @GET
    @Path("{id: \\d+}")
    public FarmaciaDetailDTO getFarmacia(@PathParam("id") Long id)throws BusinessLogicException
    {        FarmaciaEntity entity = logic.getFarmacia(id);

        
        if(entity==null)
        {
            throw new BusinessLogicException("no existe la entidad");
        }
        return new FarmaciaDetailDTO(entity);
    }
    

        /**
     * Recurso que obtiene la ubicación de una farmacia por su id
     * @param id id de la farmacia
     * @return UbicacionEntity de la farmacia
     */
    @GET
    @Path("{id: \\d+}/ubicacion")
    public UbicacionDTO getUbicacionFarmacia(@PathParam("id") Long id)
    {
        UbicacionEntity entity = logic.getFarmacia(id).getUbicacion();
        return new UbicacionDTO(entity);
    }
    
      
    /**
     * Recurso para agregar un medicamento a una farmacia y viceversa
     * @param id id de la farmacia
     * @param idMed id del medicamento 
     * @return farmacia actualizado
     * @throws BusinessLogicException si no exite farmacia o medicamento con los id dados
     */
    @PUT
    @Path("{id: \\d+}/medicamentos/{idMed: \\d+}")
    public FarmaciaDetailDTO addMedicamentoFarmacia(@PathParam("id") Long id,@PathParam("idMed") Long idMed)throws BusinessLogicException
    {
        FarmaciaEntity entity = logic.getFarmacia(id);
        if(!logicMF.agregarRelacion(id, idMed))
        {
            throw new BusinessLogicException("no existe la entidad");
        }
        return new FarmaciaDetailDTO(logic.updateFarmacia(entity));
    }
    
        /**
     * Recurso para eliminar el medicamento de una farmacia y viceversa
     * @param id id de la farmacia
     * @param idMed id del medicamento 
     * @return farmacia actualizado
     * @throws BusinessLogicException si no existe la relación
     */
    @DELETE
    @Path("/{id: \\d+}/medicamentos/{idMed: \\d+}")
    public FarmaciaDetailDTO deleteMedicamentoFarmacia(@PathParam("id") Long id,@PathParam("idMed") Long idMed)throws BusinessLogicException
    {
        FarmaciaEntity entity = logic.getFarmacia(id);
        if(!logicMF.eliminarRelacion(id, idMed))
        {
            throw new BusinessLogicException("no existe la entidad");
        }
        return new FarmaciaDetailDTO(logic.updateFarmacia(entity));
    }
    
    
    /**
     * retorna los medicamentos que vende una farmacia  
     * @param id id de la farmacia
     * @return la lista de medicamentos de la farmacia
     * @throws BusinessLogicException si no existe la farmacia con el id dado
     */
    @GET
    @Path("{id: \\d+}/medicamentos")
    public List<MedicamentoDTO> getMedicamentoFarmacia(@PathParam("id") Long id)throws BusinessLogicException
    {
        FarmaciaEntity entity = logic.getFarmacia(id);
        if(entity==null)
        {
            throw new BusinessLogicException("no existe la entidad");
        }
        List<MedicamentoDTO> list= new ArrayList<>();
        Iterator<MedicamentoEntity> iter=entity.getMedicamentos().iterator();
        while(iter.hasNext())
        {
            list.add(new MedicamentoDTO(iter.next()));
        }
        return list;
    }
    
    
    /**
     * Recurso que obtiene todos los hospitales
     * @return Lista con todos los hospitales
     */
    @GET
    public List<FarmaciaDetailDTO> getFarmacias()
    {
        return listToList(logic.getFarmacias());
        
    }
    
    private List<FarmaciaDetailDTO> listToList(List<FarmaciaEntity> entityList) {
        List<FarmaciaDetailDTO> listDTO=new LinkedList<>();
        for(FarmaciaEntity entity: entityList) {
            listDTO.add(new FarmaciaDetailDTO(entity));
        }
        return listDTO;
    }
    
    /**
     * Recurso para actualizar un farmacia
     * @param farmacia JSON con los detalles del framacia
     * @return farmacia actualizado
     */
    @PUT
    public FarmaciaDetailDTO updateFarmacia(FarmaciaDetailDTO farmacia)throws BusinessLogicException
    {
        FarmaciaEntity entity = farmacia.toEntity();
        if(logic.getFarmacia(entity.getId())==null)
        {
            throw new BusinessLogicException("no existe farmacia con el id dado");
        }
        return new FarmaciaDetailDTO(logic.updateFarmacia(entity));
    }
    
    /**
     * Recurso que elimina un farmacia
     * @param id id del farmacia
     */ 
    @Path("{id:\\d+}")
    @DELETE
    public  void deleteFarmacia(@PathParam("id") Long id) throws BusinessLogicException
    {
        logic.deleteFarmacia(id);
    }
    
}

