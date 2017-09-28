/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
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
 * @author jl.patarroyo
 */
@RunWith(Arquillian.class)
public class HospitalPersistenceTest {

    @Inject
    private HospitalPersistence persistence;

    @Inject
    private ConsultorioPersistence consultorioPersistence;

    @Inject
    private UbicacionPersistence ubicacionPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<HospitalEntity> data = new ArrayList<HospitalEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HospitalEntity.class.getPackage())
                .addPackage(HospitalPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public HospitalPersistenceTest() {
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
        em.createQuery("delete from HospitalEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 50; i++) {
            HospitalEntity entity = factory.manufacturePojo(HospitalEntity.class);

            List<ConsultorioEntity> consultorios = createConsultorios();
            UbicacionEntity ubicacion = createUbicacion();
            entity.setConsultorios(consultorios);
            entity.setUbicacion(ubicacion);

            em.persist(entity);
            data.add(entity);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class HospitalPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        HospitalEntity newEntity = factory.manufacturePojo(HospitalEntity.class);

        HospitalEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        HospitalEntity entity = em.find(HospitalEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getConsultorios(), entity.getConsultorios());
        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
    }

    /**
     * Test of delete method, of class HospitalPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        HospitalEntity entity = data.get(0);
        persistence.delete(entity.getId());
        HospitalEntity deleted = em.find(HospitalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of upadte method, of class HospitalPersistence.
     */
    @Test
    public void testUpadte() throws Exception {
        HospitalEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        HospitalEntity newEntity = factory.manufacturePojo(HospitalEntity.class);

        newEntity.setId(entity.getId());

        persistence.upadte(newEntity);

        HospitalEntity resp = em.find(HospitalEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    /**
     * Test of find method, of class HospitalPersistence.
     */
    @Test
    public void testFind() throws Exception {
        HospitalEntity entity = data.get(0);
        HospitalEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Test of findAll method, of class HospitalPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<HospitalEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (HospitalEntity ent : list) {
            boolean found = false;
            for (HospitalEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of findByReference method, of class HospitalPersistence.
     */
    @Test
    public void testFindByName() throws Exception {
        HospitalEntity entity = data.get(0);
        HospitalEntity newEntity = persistence.findByReference(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Método encargado de crear una llista de consultorios y persistir sus
     * entidades
     *
     * @return Lista de consultorioEntity
     */
    private List<ConsultorioEntity> createConsultorios() {
        PodamFactory factory = new PodamFactoryImpl();
        List<ConsultorioEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ConsultorioEntity consultorio = factory.manufacturePojo(ConsultorioEntity.class);
            list.add(consultorio);
            if (consultorioPersistence.find(consultorio.getId()) == null) {
                consultorioPersistence.create(consultorio);
            }
        }
        return list;
    }

    /**
     * Método encargado de crear una ubicación y persistirla
     *
     * @return UbicacionEntity creada
     */
    private UbicacionEntity createUbicacion() {
        PodamFactory factory = new PodamFactoryImpl();
        UbicacionEntity ubicacion = factory.manufacturePojo(UbicacionEntity.class);
        if (ubicacionPersistence.findById(ubicacion.getId()) == null) {
            ubicacionPersistence.create(ubicacion);
        }
        return ubicacion;
    }

}
