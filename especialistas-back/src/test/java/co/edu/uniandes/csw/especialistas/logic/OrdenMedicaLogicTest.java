/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.logic;

import co.edu.uniandes.csw.especialistas.ejb.OrdenMedicaLogic;
import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;
import co.edu.uniandes.csw.especialistas.persistence.OrdenMedicaPersistence;
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
public class OrdenMedicaLogicTest {
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrdenMedicaEntity.class.getPackage())
                .addPackage(OrdenMedicaPersistence.class.getPackage())
                .addPackage(OrdenMedicaLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private OrdenMedicaPersistence persistence;
    
    @Inject
    private OrdenMedicaLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<OrdenMedicaEntity> data = new ArrayList<>();
    
    public OrdenMedicaLogicTest() {
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
        em.createQuery("delete from OrdenMedicaEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            OrdenMedicaEntity entity = factory.manufacturePojo(OrdenMedicaEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    private OrdenMedicaEntity entity = new OrdenMedicaEntity();

    /**
     * Test of create method, of class OrdenMedicaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        entity = new OrdenMedicaEntity();
        logic.createOrdenMedica(entity);
        
        assertEquals(logic.getOrdenMedica(entity.getId()), entity);
    }
    
    /**
     * Test of update method, of class OrdenMedicaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        entity = new OrdenMedicaEntity();
        logic.createOrdenMedica(entity);
        entity.setDescripcion("nombre");
        assertEquals(logic.updateOrdenMedica(entity), entity);
    }
    
    /**
     * Test of findAll method, of class OrdenMedicaPersistence.
     */
    @Test
    public void testGet() throws Exception {
        entity = new OrdenMedicaEntity();
        logic.createOrdenMedica(entity);
        assertEquals(logic.getOrdenesMedicas().contains(entity), true);
    }
    
    /**
     * Test of delete method, of class OrdenMedicaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        entity = new OrdenMedicaEntity();
        logic.createOrdenMedica(entity);
        logic.deleteOrdenMedica(entity.getId());
        assertEquals(logic.getOrdenMedica(entity.getId()), null);
    }
    
}
