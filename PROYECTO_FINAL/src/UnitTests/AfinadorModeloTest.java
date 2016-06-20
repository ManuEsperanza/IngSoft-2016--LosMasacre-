
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class AfinadorModeloTest {
    
    public AfinadorModeloTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setBPM method, of class AfinadorModelo.
     */
    @Test
    public void testSetBPM() {
        System.out.println("setBPM");
        int nota = 2;
        AfinadorModelo instance = new AfinadorModelo();
        instance.setBPM(nota);
        // TODO review the generated test code and remove the default call to fail.
        if (nota !=instance.nota )
        fail("Error en test de metodo setBPM");
    }

    /**
     * Test of getBPM method, of class AfinadorModelo.
     */
    @Test
    public void testGetBPM() {
        System.out.println("getBPM");
        AfinadorModelo instance = new AfinadorModelo();
        int expResult = 0;
        int result = instance.getBPM();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (result != expResult)
        fail("Error en test de metodo getBPM");
    }

    /**
     * Test of registerObserver method, of class AfinadorModelo.
     */
    @Test
    public void testRegisterObserver_BeatObserver() {
        System.out.println("registerObserver");
        BeatObserver o = null;
        AfinadorModelo instance = new AfinadorModelo();
        instance.registerObserver(o);
        if (instance.beatObservers.size() == 0){
        fail("Error en test de metodo RegisterObserver_BeatObserver ");
        }
   }

    
   @Test
    public void testRemoveObserver_BeatObserver() {
        System.out.println("removeObserver");
        BeatObserver o = null;
        AfinadorModelo instance = new AfinadorModelo();
        instance.registerObserver(o);
        instance.removeObserver(o);
        if (instance.beatObservers.size()!=0)
        fail("Error en test de metodo RemoveObserver_BeatObserver");
    }

   
}
