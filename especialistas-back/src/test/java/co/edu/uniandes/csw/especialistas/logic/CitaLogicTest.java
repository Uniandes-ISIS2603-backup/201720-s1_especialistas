/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.logic;

import co.edu.uniandes.csw.especialistas.ejb.CitaLogic;
import co.edu.uniandes.csw.especialistas.entities.CitaEntity;
import co.edu.uniandes.csw.especialistas.persistence.CitaPersistence;
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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ce.quintero
 */
@RunWith(Arquillian.class)
public class CitaLogicTest {
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CitaEntity.class.getPackage())
                .addPackage(CitaPersistence.class.getPackage())
                .addPackage(CitaLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private CitaPersistence persistence;
    
    @Inject
    private CitaLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<CitaEntity> data = new ArrayList<>();
    
    public CitaLogicTest() {
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
            try{
                utx.rollback();
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
    
    
    private void clearData() {
        em.createQuery("delete from CitaEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            CitaEntity entity = factory.manufacturePojo(CitaEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    private CitaEntity entity = new CitaEntity();

    /**
     * Test of create method, of class CitaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        entity = new CitaEntity();
        logic.createCita(entity);
        
        assertEquals(logic.getCita(entity.getId()), entity);
    }
    
    /**
     * Test of update method, of class CitaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        entity = new CitaEntity();
        logic.createCita(entity);
        entity.setComentarios("nombre");
        assertEquals(logic.updateCita(entity), entity);
    }
    
    /**
     * Test of findAll method, of class CitaPersistence.
     */
    @Test
    public void testGet() throws Exception {
        entity = new CitaEntity();
        logic.createCita(entity);
        assertEquals(logic.getCitas().contains(entity), true);
    }
    
    /**
     * Test of delete method, of class CitaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        entity = new CitaEntity();
        logic.createCita(entity);
        logic.deleteCita(entity.getId());
        assertEquals(logic.getCita(entity.getId()), null);
    }
    
}
