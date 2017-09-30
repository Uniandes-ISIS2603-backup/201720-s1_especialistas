/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Clase que indica que este proyecto web ofrece servicios REST. Adicionalmente,
 * esta clase define el prefijo por defecto de las rutas a los recursos.
 *
 * (non-Javadoc)
 *
 * @see javax.ws.rs.core.Application
 * @author js.diaz
 */
@ApplicationPath("/api")
public class RestConfig extends Application{
    
}
