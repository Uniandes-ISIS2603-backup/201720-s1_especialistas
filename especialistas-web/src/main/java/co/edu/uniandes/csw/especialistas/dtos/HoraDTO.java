/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import java.util.Date;

/**
 *
 * @author JuanSebastian
 */
public class HoraDTO {
    
    private Date horaInicio;
    
    private Date horaFin;
    
    public HoraDTO(){
        
    }
    
   public HoraDTO(HoraEntity entity){
       this.horaInicio = entity.getHoraInicio();
       this.horaFin = entity.getHoraFin();
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
        entity.setHoraInicio(horaInicio);
        entity.setHoraFin(horaFin);
        return entity;
    }
    
}
