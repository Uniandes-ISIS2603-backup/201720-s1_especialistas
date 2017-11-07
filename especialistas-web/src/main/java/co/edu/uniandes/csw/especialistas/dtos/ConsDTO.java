/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ConsEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;

/**
 *
 * @author rc.tejon
 */
public class ConsDTO extends EspecialistasDTO {

    private String numero;

    public ConsDTO() {
        //inicialmente vacio
    }

    public ConsDTO(ConsEntity entity) {
        id = entity.getId();
        numero = entity.getNumero();
    }

    public String getNumero() {
        return numero;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ConsEntity toEntity() {
        ConsEntity entity = new ConsEntity();
        entity.setId(this.id);
        entity.setNumero(this.numero);
        return entity;
    }

}
