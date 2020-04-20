import org.easymock.EasyMock;
import org.easymock.MockType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static org.easymock.EasyMock.*;

public class EasyMockAndMockitoTest {
    private Car myFerrari = mock(Car.class);
    private Car myFerrariEasy = createMock(MockType.NICE,Car.class);
    
    // W Easymock trzeba verify wszystkiego, w mockito nie ma takiego obowiązku
    
    @Test
    public void testVerification(){
        when(myFerrari.needsFuel()).thenReturn(true);
        when(myFerrari.getEngineTemperature()).thenReturn(35.5);
        myFerrari.driveTo("Kartuzy");
        myFerrari.needsFuel();
        myFerrari.getEngineTemperature();
        
        verify(myFerrari).needsFuel();
        assertTrue(myFerrari.needsFuel());
        assertEquals(35.5,myFerrari.getEngineTemperature());
    }
    
    @Test
    public void testVerificationEasy(){
        expect(myFerrariEasy.needsFuel()).andReturn(true);
        expect(myFerrariEasy.getEngineTemperature()).andReturn(35.5);
        replay(myFerrariEasy);
        
        assertTrue(myFerrariEasy.needsFuel());
        assertEquals(35.5,myFerrariEasy.getEngineTemperature());
        EasyMock.verify(myFerrariEasy);
    }
    
}
