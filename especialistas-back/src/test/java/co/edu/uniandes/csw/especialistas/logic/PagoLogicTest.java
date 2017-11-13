/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.logic;

import co.edu.uniandes.csw.especialistas.ejb.PagoLogic;
import co.edu.uniandes.csw.especialistas.entities.PagoEntity;
import co.edu.uniandes.csw.especialistas.persistence.PagoPersistence;
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
public class PagoLogicTest {
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addPackage(PagoLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private PagoPersistence persistence;
    
    @Inject
    private PagoLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<PagoEntity> data = new ArrayList<>();
    
    public PagoLogicTest() {
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
        em.createQuery("delete from PagoEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    private PagoEntity entity = new PagoEntity();

    /**
     * Test of create method, of class PagoPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        entity = new PagoEntity();
        logic.createPago(entity);
        
        assertEquals(logic.getPago(entity.getId()), entity);
        assertEquals(logic.getPagoById(entity.getId()), entity);
    }
    
    /**
     * Test of update method, of class PagoPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        entity = new PagoEntity();
        logic.createPago(entity);
        entity.setMetodo("nombre");
        assertEquals(logic.updatePago(entity), entity);
    }
    
    /**
     * Test of findAll method, of class PagoPersistence.
     */
    @Test
    public void testGet() throws Exception {
        entity = new PagoEntity();
        logic.createPago(entity);
        assertEquals(logic.getPagos().contains(entity), true);
    }
    
    /**
     * Test of delete method, of class PagoPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        entity = new PagoEntity();
        logic.createPago(entity);
        logic.deletePago(entity.getId());
        assertEquals(logic.getPago(entity.getId()), null);
    }
    
}
