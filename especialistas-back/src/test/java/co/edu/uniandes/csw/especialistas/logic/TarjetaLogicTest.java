/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.logic;

import co.edu.uniandes.csw.especialistas.ejb.TarjetaLogic;
import co.edu.uniandes.csw.especialistas.entities.TarjetaEntity;
import co.edu.uniandes.csw.especialistas.persistence.TarjetaPersistence;
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
public class TarjetaLogicTest {
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaEntity.class.getPackage())
                .addPackage(TarjetaPersistence.class.getPackage())
                .addPackage(TarjetaLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private TarjetaPersistence persistence;
    
    @Inject
    private TarjetaLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<TarjetaEntity> data = new ArrayList<>();
    
    public TarjetaLogicTest() {
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
        em.createQuery("delete from TarjetaEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            TarjetaEntity entity = factory.manufacturePojo(TarjetaEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    private TarjetaEntity entity = new TarjetaEntity();

    /**
     * Test of create method, of class TarjetaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        entity = new TarjetaEntity();
        logic.createTarjeta(entity);
        
        assertEquals(logic.getTarjeta(entity.getId()), entity);
    }
    
    /**
     * Test of update method, of class TarjetaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        entity = new TarjetaEntity();
        logic.createTarjeta(entity);
        entity.setNombre("nombre");
        assertEquals(logic.updateTarjeta(entity), entity);
    }
    
    /**
     * Test of findAll method, of class TarjetaPersistence.
     */
    @Test
    public void testGet() throws Exception {
        entity = new TarjetaEntity();
        logic.createTarjeta(entity);
        assertEquals(logic.getTarjetas().contains(entity), true);
    }
    
    /**
     * Test of delete method, of class TarjetaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        entity = new TarjetaEntity();
        logic.createTarjeta(entity);
        logic.deleteTarjeta(entity.getId());
        assertEquals(logic.getTarjeta(entity.getId()), null);
    }
    
}
