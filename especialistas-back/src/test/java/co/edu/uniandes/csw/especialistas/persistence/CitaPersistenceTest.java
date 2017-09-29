/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.CitaEntity;
import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;
import co.edu.uniandes.csw.especialistas.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jr.restom10
 */
@RunWith(Arquillian.class)
public class CitaPersistenceTest {

    /**
     * Inyección de la dependencia a la clase CitaPersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    private CitaPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    @Inject
    private OrdenMedicaPersistence ordenPersistencia;

    @Inject
    private HoraPersistence horaPersistencia;
    
    @Inject
    private UsuarioPersistence usuarioPersistencia;

    /**
     *
     */
    private List<CitaEntity> data = new ArrayList<CitaEntity>();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Cita, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CitaEntity.class.getPackage())
                .addPackage(CitaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public CitaPersistenceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from CitaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i <10 ; i++) {
            CitaEntity entity = factory.manufacturePojo(CitaEntity.class);
            //List<OrdenMedicaEntity> ordenes = createOrdenesMedicas();
            UsuarioEntity usuario = createUsuario();
            HoraEntity hora = createHora();
            entity.setHora(hora);
            //entity.setOrdenMedica(ordenes);
            entity.setUsuario(usuario);
            
            em.persist(entity);
            data.add(entity);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class CitaPersistence.
     */
    @Test
    public void createCitaEntityTest() {
        PodamFactory factory = new PodamFactoryImpl();

        CitaEntity newEntity = factory.manufacturePojo(CitaEntity.class);

        CitaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        CitaEntity entity = em.find(CitaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
        Assert.assertEquals(newEntity.getHora(), entity.getHora());
        //Assert.assertEquals(newEntity.getOrdenesMedicas(), entity.getOrdenesMedicas());
        
    }

    /**
     * Test of findById method, of class CitaPersistence.
     */
    @Test
    public void getCitaTest() {
        CitaEntity entity = data.get(0);
        CitaEntity newEntity = persistence.findById(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Test of deleteById method, of class CitaPersistence.
     */
    @Test
    public void deleteCitaTest() {
        CitaEntity entity = data.get(0);
        persistence.deleteById(entity.getId());
        CitaEntity deleted = em.find(CitaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of update method, of class CitaPersistence.
     */
    @Test
    public void updateCitaTest() {
        CitaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CitaEntity newEntity = factory.manufacturePojo(CitaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        CitaEntity resp = em.find(CitaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Test of findAll method, of class CitaPersistence.
     */
    @Test
    public void getCitasTest() {
        List<CitaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CitaEntity ent : list) {
            boolean found = false;
            for (CitaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Método encargado de crear una lista de ordenes médicas y persistirlas
     *
     * @return Lista con OrdenMedicaEntity
     */
    private List<OrdenMedicaEntity> createOrdenesMedicas() {
        PodamFactory factory = new PodamFactoryImpl();
        List<OrdenMedicaEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            OrdenMedicaEntity orden = factory.manufacturePojo(OrdenMedicaEntity.class);
            list.add(orden);
            if (ordenPersistencia.findById(orden.getId()) == null) {
                ordenPersistencia.create(orden);
            }
        }
        return list;
    }

    /**
     * Método encargado de crear y persistir una hora
     *
     * @return Entidad de la hora
     */
    private HoraEntity createHora() {
        PodamFactory factory = new PodamFactoryImpl();
        HoraEntity hora = factory.manufacturePojo(HoraEntity.class);
        if (horaPersistencia.find(hora.getId()) == null) {
            horaPersistencia.create(hora);
        }
        return hora;
    }

    /**
     * Método encargado de crear un usuario y persistirlo
     * @return Usuario creado
     */
    private UsuarioEntity createUsuario() {
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
        if (usuarioPersistencia.find(usuario.getId()) == null) {
            usuarioPersistencia.create(usuario);
        }
        return usuario;
    }
}
