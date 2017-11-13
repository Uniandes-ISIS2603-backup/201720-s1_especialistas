/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.CitaEntity;
import co.edu.uniandes.csw.especialistas.entities.PagoEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import co.edu.uniandes.csw.especialistas.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ce.quintero
 */
public class UsuarioDetailDTO extends UsuarioDTO{
    
    /**
     * atributo que modela la targeta del usuario
     */
    private TarjetaDTO tarjeta;
    
    /**
     * lista de citasmedicas de un usuario
     */
    private List <CitaDTO> citasMedicas = new ArrayList<>();
    
    /**
     * lista de pagos de un usuario
     */
    private List <PagoDTO> pagos = new ArrayList<>();
    
    /**
     * Ubicacion del usuario
     */
    private UbicacionEntity ubicacion;
    
    
    /**
     * Constructor por defecto
     */
    public UsuarioDetailDTO()
    {
        //crea un usuario detail vacio
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity Entidad que contiene la información
     */
    public UsuarioDetailDTO(UsuarioEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            if(entity.getTarjeta() != null)
            {
                TarjetaDTO tarjeta2 = new TarjetaDTO(entity.getTarjeta());
                this.tarjeta = tarjeta2;
            }
            
            citasMedicas = new ArrayList<>();
            for (CitaEntity cita : entity.getCitas()) {
                citasMedicas.add(new CitaDTO(cita));
            }
            
            pagos = new ArrayList<>();
            for (PagoEntity pago : entity.getPagos()) {
                pagos.add(new PagoDTO(pago));
            }
            
        }
            
    }
    
    /**
     * Método encargado de convertir el detail a entity
     * @return UsuarioEntity con la información
     */
    @Override
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = super.toEntity();
        if(tarjeta != null)
            entity.setTarjeta(tarjeta.toEntity());
        if(entity.getCitas()!= null)
            {
                List<CitaEntity> citaEntity = new ArrayList<>();
                for (CitaDTO cita : citasMedicas){
                    citaEntity.add(cita.toEntity());
                }
                entity.setCitas(citaEntity);
            }
        if(pagos != null)
            {
                List<PagoEntity> pagoEntity = new ArrayList<>();
                for (PagoDTO pago : pagos){
                    pagoEntity.add(pago.toEntity());
                }
                entity.setPagos(pagoEntity);
            }
        return entity;
    }
    
    /**
     * Getter de targeta
     * @return 
     */
    public TarjetaDTO getTarjeta() {
        return this.tarjeta;
    }

    /**
     * Setter de targeta
     * @param tarjeta 
     */
    public void setTarjeta(TarjetaDTO tarjeta) {
        this.tarjeta = tarjeta;
    }
    
    /**
     * Getter de los citas
     * @return 
     */
    public List<CitaDTO> getCitas() {
        return this.citasMedicas;
    }

    /**
     * Setter de los citas
     * @param citasMedicas Lista de citas medicas
     */
    public void setCitas(List<CitaDTO> citasMedicas) {
        this.citasMedicas = citasMedicas;
    }
    
    /**
     * Getter de los pagos
     * @return 
     */
    public List<PagoDTO> getPagos() {
        return this.pagos;
    }

    /**
     * Setter de los pagos
     * @param pagos Lista de pagos
     */
    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }
    
    /**
     * Getter de la ubicación
     * @return Ubicación del hospital
     */
    public UbicacionEntity getUbicacion() {
        return ubicacion;
    }

    /**
     * Setter de la ubicación
     * @param ubicacion Ubicación del hospital
     */
    public void setUbicacion(UbicacionEntity ubicacion) {
        this.ubicacion = ubicacion;
    }
}
