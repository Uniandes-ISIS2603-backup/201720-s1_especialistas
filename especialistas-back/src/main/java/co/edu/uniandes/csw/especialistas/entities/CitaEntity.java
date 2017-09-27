/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author jr.restom10
 */
@Entity
public class CitaEntity extends BaseEntity{
        
        
         private String comentarios;
         
         @OneToOne()
         @JoinColumn(name="HORA_ID")
         private HoraEntity hora;
         
         @OneToMany(mappedBy="cita")
         @JoinColumn(name="ORDEN_ID")
         private List <OrdenMedicaEntity> ordenesMedicas;
        
         @ManyToOne(fetch=FetchType.LAZY)
         @JoinColumn(name="USUARIO_ID")
         private UsuarioEntity usuario;
        
         
         public void setHora(HoraEntity s)
        {
            hora=s;
        }
        
         public HoraEntity getHora()
        {
            return hora;
        }
         
         public void setOrdenMedica(List <OrdenMedicaEntity> s)
        {
            ordenesMedicas=s;
        }
        
         public List <OrdenMedicaEntity> getOrdenesMedicas()
        {
            return ordenesMedicas;
        }
         
        public void setUsuario(UsuarioEntity s)
        {
            usuario=s;
        }
        
         public UsuarioEntity getUsuario()
        {
            return usuario;
        }
         
        public void setComentarios(String s)
        {
            comentarios=s;
        }
        
         public String getComentarios()
        {
            return comentarios;
        }


    
}
