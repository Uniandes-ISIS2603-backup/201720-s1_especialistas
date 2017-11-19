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
    
    private Date horaInicioD;
    
    private Date horaFinD;
    
    public HoraDTO(){
        //inicialmenete vacio
    }
    
   public HoraDTO(HoraEntity entity){
       this.id = entity.getId();
       this.horaInicioD = entity.getHoraInicio();
       this.horaFinD = entity.getHoraFin();
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Date getHoraInicio() {
        return horaInicioD;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicioD = horaInicio;
    }

    public Date getHoraFin() {
        return horaFinD;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFinD = horaFin;
    }
    
    public HoraEntity toEntity(){
        HoraEntity entity = new HoraEntity();
        entity.setId(id);
        entity.setHoraInicio(horaInicioD);
        entity.setHoraFin(horaFinD);
        return entity;
    }
}
