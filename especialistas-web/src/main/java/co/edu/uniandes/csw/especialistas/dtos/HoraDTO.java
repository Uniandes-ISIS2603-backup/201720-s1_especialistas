/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import java.util.Date;

/**
 * @author JuanSebastian
 */
public class HoraDTO {
    
    private Long id;
    
    private Date horaInicio;
    
    private Date horaFin;
    
    public HoraDTO(){
        //inicialmenete vacio
    }
    
   public HoraDTO(HoraEntity entity){
       this.id = entity.getId();
       this.horaInicio = entity.getHoraInicio();
       this.horaFin = entity.getHoraFin();
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }
    
    public HoraEntity toEntity(){
        HoraEntity entity = new HoraEntity();
        entity.setId(id);
        entity.setHoraInicio(horaInicio);
        entity.setHoraFin(horaFin);
        return entity;
    }
}
