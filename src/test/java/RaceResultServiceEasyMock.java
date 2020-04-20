
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.easymock.EasyMock.*;


public class RaceResultServiceEasyMock {
    
    private Client client;
    private RaceResultService raceResultService;
    private Message message;
    private Collection<Client> clients;
    
    
    @BeforeEach
    public void setup(){
        client=mock(Client.class);
        raceResultService=new RaceResultService();
        message=mock(Message.class);
        clients = mock(HashSet.class);
        raceResultService.setClients(clients);
    }
    
    @Test
    public void testAddSubscriber(){
        replay(client);
        raceResultService.addSubscriber(client);
        verify(client);
    }
    
    @Test
    public void testRemoveSubscriber(){
        replay(client);
        raceResultService.removeSubscriber(client);
        verify(client);
    }
    
    @AfterEach
    public void teardown(){
        client=null;
        raceResultService=null;
        message=null;
        clients=null;
    }
}
