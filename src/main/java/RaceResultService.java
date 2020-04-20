import java.util.Collection;
import java.util.HashSet;

public class RaceResultService {
    private Collection<Client> clients = new HashSet<>();
    
    public void addSubscriber(Client client){
        clients.add(client);
    }
    
    public void send(Message message){
        for (Client client: clients){
            client.receive(message);
        }
    }
    
    public void setClients(Collection<Client> clients){
        this.clients=clients;
    }
    
    public void removeSubscriber(Client client){
        clients.remove(client);
    }
}