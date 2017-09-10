/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
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
 * @author rc.tejon
 */
@RunWith(Arquillian.class)
public class MedicamentoPersistenceTest {
    @PersistenceContext
    private EntityManager em;


    @Inject
    UserTransaction utx;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedicamentoEntity.class.getPackage())
                .addPackage(MedicamentoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public MedicamentoPersistenceTest() {
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
    
    @After
    public void tearDown() {
    }

   
    @Inject
    private MedicamentoPersistence medicamentoPersistence;

    private void clearData() {
        em.createQuery("delete from MedicamentoEntity").executeUpdate();
    }

    private List<MedicamentoEntity> data = new ArrayList<MedicamentoEntity>();

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 100; i++) {
            MedicamentoEntity entity = factory.manufacturePojo(MedicamentoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createMedicamentoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MedicamentoEntity newEntity = factory.manufacturePojo(MedicamentoEntity.class);
        MedicamentoEntity result = medicamentoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MedicamentoEntity entity = em.find(MedicamentoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
     
    }

    /**
     * Prueba para consultar la lista de Companys.
     *
     *
     */
    @Test
    public void getMedicamentosTest() {
        List<MedicamentoEntity> list = medicamentoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MedicamentoEntity ent : list) {
            boolean found = false;
            for (MedicamentoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Company.
     *
     *
     */
    @Test
    public void getMedicamentoTest() {
        MedicamentoEntity entity = data.get(0);
        MedicamentoEntity newEntity = medicamentoPersistence.findById(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
      
    }


    @Test
    public void deleteMedicamentoTest() {
        MedicamentoEntity entity = data.get(0);
        medicamentoPersistence.deleteById(entity.getId());
        MedicamentoEntity deleted = em.find(MedicamentoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateMedicamentoTest() {
        MedicamentoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MedicamentoEntity newEntity = factory.manufacturePojo(MedicamentoEntity.class);

        newEntity.setId(entity.getId());

        medicamentoPersistence.update(newEntity);

        MedicamentoEntity resp = em.find(MedicamentoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
      
    }
}
