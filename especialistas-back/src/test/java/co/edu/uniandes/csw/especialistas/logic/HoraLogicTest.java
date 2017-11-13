/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.logic;

import co.edu.uniandes.csw.especialistas.ejb.HoraLogic;
import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import co.edu.uniandes.csw.especialistas.persistence.HoraPersistence;
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
public class HoraLogicTest{
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HoraEntity.class.getPackage())
                .addPackage(HoraPersistence.class.getPackage())
                .addPackage(HoraLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private HoraPersistence persistence;
    
    @Inject
    private HoraLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<HoraEntity> data = new ArrayList<>();
    
    public HoraLogicTest() {
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
        em.createQuery("delete from HoraEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            HoraEntity entity = factory.manufacturePojo(HoraEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    private HoraEntity entity = new HoraEntity();

    /**
     * Test of create method, of class HoraPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        entity = new HoraEntity();
        logic.createHora(entity);
        
        assertEquals(logic.getHora(entity.getId()), entity);
    }
    
    /**
     * Test of update method, of class HoraPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        entity = new HoraEntity();
        logic.createHora(entity);
        assertEquals(logic.updateHora(entity.getId(),entity), entity);
    }
    
    /**
     * Test of findAll method, of class HoraPersistence.
     */
    @Test
    public void testGet() throws Exception {
        entity = new HoraEntity();
        logic.createHora(entity);
        assertEquals(logic.getHoras().contains(entity), true);
    }
    
    /**
     * Test of delete method, of class HoraPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        entity = new HoraEntity();
        logic.createHora(entity);
        logic.deleteHora(entity.getId());
        assertEquals(logic.getHora(entity.getId()), null);
    }
    
}
