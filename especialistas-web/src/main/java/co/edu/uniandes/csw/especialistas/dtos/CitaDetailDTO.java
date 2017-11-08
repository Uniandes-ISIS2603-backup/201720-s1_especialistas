/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.CitaEntity;
import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jr.restom10
 */
public class CitaDetailDTO extends CitaDTO{
    
     private HoraDTO hora;
     private UsuarioDTO usuario;
     private List<OrdenMedicaDTO> ordenesMedicas;
     
     
     public CitaDetailDTO() {
         //inicialmente vacio
    }

    public CitaDetailDTO(CitaEntity entity) {
        super(entity);
        
        if(entity.getOrdenesMedicas()!=null)
        {
            ordenesMedicas = new ArrayList();
        
        for(OrdenMedicaEntity orden:entity.getOrdenesMedicas())
        {
            ordenesMedicas.add(new OrdenMedicaDTO(orden));
        }
        
        }
        if(entity.getHora()!=null)
        {
        this.hora = new HoraDTO(entity.getHora());
        }
        
        if(entity.getUsuario()!=null)
        {
        this.usuario = new UsuarioDTO(entity.getUsuario());
        }
    }

    public HoraDTO getHora() {
        return hora;
    }
    public List<OrdenMedicaDTO> getOrdenesMedicas() {
        return ordenesMedicas;
    }
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setHora(HoraDTO hora) {
        this.hora = hora;
    }
     public void setOrdenesMedicas(List<OrdenMedicaDTO> orden) {
        this.ordenesMedicas = orden;
    }
      public void setUsuario(UsuarioDTO usuario) {
        this.usuario= usuario;
    }

    @Override
    public CitaEntity toEntity() {
        CitaEntity entity = super.toEntity();
        if(hora!=null){
            entity.setHora(hora.toEntity());
        }
        if(usuario!=null){
        entity.setUsuario(usuario.toEntity());
        }
        if(this.getOrdenesMedicas()!=null)
        {
        List<OrdenMedicaEntity> o= new ArrayList();
        for(OrdenMedicaDTO orden:this.getOrdenesMedicas() )
        {
            o.add(orden.toEntity());
        }
        
        
        entity.setOrdenesMedicas(o);
        }
        return entity;
    }

    
    
    
    
    
    
    
    
}
