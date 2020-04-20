import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CarTest {
    private Car myFerrari = mock(Car.class);
    
    @Test
    public void test_instance_car(){
        assertTrue(myFerrari instanceof Car);
    }
    
    @Test
    public void test_default_behavior_needsFuel(){
        assertFalse(myFerrari.needsFuel(), "New test double should return False as boolean");
    }
    
    @Test
    public void test_default_behavior_temperature(){
        assertEquals(0.0, myFerrari.getEngineTemperature(), "New test double should return 0.0");
    }
    
    @Test
    public void test_stubbing_mock(){
        when(myFerrari.needsFuel()).thenReturn(true);
        assertTrue(myFerrari.needsFuel());
    }
    
    @Test
    public void test_exception(){
        when(myFerrari.needsFuel()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> {
            myFerrari.needsFuel();
        });
    }
    
    @Test
    public void testVerification(){
        myFerrari.driveTo("Kartuzy");
        myFerrari.needsFuel();
        
        verify(myFerrari).driveTo("Kartuzy");
        verify(myFerrari).needsFuel();
        assertFalse(myFerrari.needsFuel());
    }
    
    @Test
    public void testTemperature(){
        when(myFerrari.getEngineTemperature()).thenReturn(65.0);
        assertEquals(65.0,myFerrari.getEngineTemperature());
    }
    
    @Test
    public void testDriveToEmptyString(){
        doThrow(new IllegalArgumentException()).when(myFerrari)
                .driveTo("");
        assertThrows(IllegalArgumentException.class, () -> myFerrari.driveTo(""));
    }
    
    
    @Test
    public void testDriveToNull(){
        doThrow(new NullPointerException()).when(myFerrari)
                .driveTo(null);
        assertThrows(NullPointerException.class, () -> myFerrari.driveTo(null));
    }
    
    @Test
    public void testAnotherVerification(){
        myFerrari.driveTo("Kartuzy");
        myFerrari.needsFuel();
        myFerrari.needsFuel();
        myFerrari.needsFuel();
        
        verify(myFerrari).driveTo("Kartuzy");
        verify(myFerrari,times(3)).needsFuel();
        verify(myFerrari,never()).getEngineTemperature();
        assertFalse(myFerrari.needsFuel());
    }
    
    
}