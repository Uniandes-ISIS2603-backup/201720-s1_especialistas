/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.FarmaciaDTO;
import co.edu.uniandes.csw.especialistas.dtos.FarmaciaDetailDTO;
<<<<<<< Updated upstream
import co.edu.uniandes.csw.especialistas.ejb.FarmaciaLogic;
import javax.persistence.EntityManager;
import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import java.net.URI;
=======
import co.edu.uniandes.csw.especialistas.dtos.MedicamentoDTO;
import co.edu.uniandes.csw.especialistas.dtos.UbicacionDTO;
import co.edu.uniandes.csw.especialistas.ejb.FarmaciaLogic;
import co.edu.uniandes.csw.especialistas.ejb.Medicamento_FarmaciaLogic;
import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
>>>>>>> Stashed changes
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
import javax.ws.rs.core.MediaType;


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
     * Clase de la lógica   
     */
    @Inject
    FarmaciaLogic logic;
    
<<<<<<< Updated upstream
=======
  //  @Inject
   // Medicamento_FarmaciaLogic logicMF;
>>>>>>> Stashed changes
    /**
     * Recurso que crea un farmacia
     * @param farmacia JSON con la información del farmacia
     * @return Entidad del hospital creado
     */
    @POST
    public FarmaciaDetailDTO createFarmacia(FarmaciaDTO farmacia)
    {
        FarmaciaEntity entity = farmacia.toEntity();
        logic.createFarmacia(entity);
        return new FarmaciaDetailDTO(entity);
    }
    
    /**
     * Recurso que obtiene un farmacia por su id
     * @param id id del farmacia
     * @return FarmaciaEntity del hospital
     */
    @GET
    @Path("{id: \\d+}")
    public FarmaciaDetailDTO getFarmacia(@PathParam("id") Long id)
    {
        FarmaciaEntity entity = logic.getFarmacia(id);
        return new FarmaciaDetailDTO(entity);
    }
    
<<<<<<< Updated upstream
=======
        /**
     * Recurso que obtiene una farmacia por su id
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
     * Recurso para actualizar un farmacia
     * @param farmacia JSON con los detalles del framacia
     * @return farmacia actualizado
     */
    @PUT
    @Path("{id: \\d+}/medicamentos/{idMed: \\d+}")
    public FarmaciaDetailDTO addMedicamentoFarmacia(@PathParam("id") Long id,@PathParam("idMed") Long idMed)throws Exception
    {
        FarmaciaEntity entity = logic.getFarmacia(id);
       /* if(!logicMF.agregarRelacion(id, idMed))
        {
            throw new Exception("no existe la entidad");
        }*/
        return new FarmaciaDetailDTO(logic.updateFarmacia(entity));
    }
    
    
    
    @GET
    @Path("{id: \\d+}/medicamentos")
    public List<MedicamentoDTO> getMedicamentoFarmacia(@PathParam("id") Long id)throws Exception
    {
        FarmaciaEntity entity = logic.getFarmacia(id);
        if(entity==null)
        {
            throw new Exception("no existe la entidad");
        }
        List<MedicamentoDTO> list= new ArrayList<>();
        Iterator<MedicamentoEntity> iter=entity.getMedicamentos().iterator();
        while(iter.hasNext())
        {
            list.add(new MedicamentoDTO(iter.next()));
        }
        return list;
    }
    
    
>>>>>>> Stashed changes
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
       /* Iterator<FarmaciaEntity> iter = entityList.iterator();
        while(iter.hasNext())
        {
            listDTO.add(new FarmaciaDetailDTO(iter.next()));
        }    */
        System.out.println("JOJO****************************");
        return listDTO;
    }
    
    /**
     * Recurso para actualizar un farmacia
     * @param farmacia JSON con los detalles del framacia
     * @return farmacia actualizado
     */
    @PUT
    public FarmaciaDetailDTO updateFarmacia(FarmaciaDetailDTO farmacia)throws Exception
    {
        FarmaciaEntity entity = farmacia.toEntity();
        if(logic.getFarmacia(entity.getId())==null)
        {
            throw new Exception("ponga el id");
        }
        return new FarmaciaDetailDTO(logic.updateFarmacia(entity));
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
