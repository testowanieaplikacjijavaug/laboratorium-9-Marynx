import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
public class RaceResultServiceMockito {
    
    private Client client;
    private RaceResultService raceResultService;
    private Message message;
    private Collection<Client> clients;
    
    @BeforeEach
    public void setup(){
        client=mock(Client.class);
        raceResultService=new RaceResultService();
        message=mock(Message.class);
        clients = spy(new HashSet<>());
        raceResultService.setClients(clients);
    }
    
    @Test
    public void testAddSubscriber(){
        raceResultService.addSubscriber(client);
        
        assertThat(clients).hasSize(1).isNotEmpty().contains(client);
        verify(clients).add(client);
    }
    
    @Test
    public void testSendMessage(){
        raceResultService.addSubscriber(client);
        raceResultService.send(message);
        assertThat(clients).hasSize(1).isNotEmpty().contains(client);
        verify(client).receive(message);
    }
    
    
    @Test
    public void testRemoveSubscriber(){
        raceResultService.addSubscriber(client);
        raceResultService.removeSubscriber(client);
        assertThat(clients).hasSize(0).isEmpty();
        verify(clients).remove(client);
    }
    
    @AfterEach
    public void teardown(){
        client=null;
        raceResultService=null;
        message=null;
        clients=null;
    }
    
    
    
}
