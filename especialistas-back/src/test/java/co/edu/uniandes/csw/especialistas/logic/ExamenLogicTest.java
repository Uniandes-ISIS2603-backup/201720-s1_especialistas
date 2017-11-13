/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.logic;

import co.edu.uniandes.csw.especialistas.ejb.ExamenLogic;
import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;
import co.edu.uniandes.csw.especialistas.persistence.ExamenPersistence;
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
public class ExamenLogicTest {
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ExamenEntity.class.getPackage())
                .addPackage(ExamenPersistence.class.getPackage())
                .addPackage(ExamenLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private ExamenPersistence persistence;
    
    @Inject
    private ExamenLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ExamenEntity> data = new ArrayList<>();
    
    public ExamenLogicTest() {
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
        em.createQuery("delete from ExamenEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            ExamenEntity entity = factory.manufacturePojo(ExamenEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    private ExamenEntity entity = new ExamenEntity();

    /**
     * Test of create method, of class ExamenPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        entity = new ExamenEntity();
        logic.createExamen(entity);
        
        assertEquals(logic.getExamenById(entity.getId()), entity);
        assertEquals(logic.getExamenById(entity.getId()), entity);
    }
    
    /**
     * Test of update method, of class ExamenPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        entity = new ExamenEntity();
        logic.createExamen(entity);
        entity.setNombre("nombre");
        assertEquals(logic.updateExamen(entity), entity);
    }
    
    /**
     * Test of findAll method, of class ExamenPersistence.
     */
    @Test
    public void testGet() throws Exception {
        entity = new ExamenEntity();
        logic.createExamen(entity);
        assertEquals(logic.getExamenes().contains(entity), true);
    }
    
    /**
     * Test of delete method, of class ExamenPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        entity = new ExamenEntity();
        logic.createExamen(entity);
        logic.deleteExamen(entity.getId());
        assertEquals(logic.getExamenById(entity.getId()), null);
    }
    
}
