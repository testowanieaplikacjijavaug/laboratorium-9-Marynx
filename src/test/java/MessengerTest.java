import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MessengerTest {

    private Client client;
    private Template template;
    private Messenger messenger;
    private MailServer mailServer;
    private TemplateEngine templateEngine;
    private final String email="test@test.com";
    private final String msg="test";
    
    
    @Test
    public void testMock(){
        client=mock(Client.class);
        template=mock(Template.class);
        mailServer=mock(MailServer.class);
        templateEngine=mock(TemplateEngine.class);
        messenger=new Messenger(mailServer,templateEngine);
        
        
        when(templateEngine.prepareMessage(template,client)).thenReturn(msg);
        when(client.getEmail()).thenReturn(email);
        
        messenger.sendMessage(client,template);
        
        verify(client).getEmail();
        verify(templateEngine).prepareMessage(template,client);
        
        assertThat(templateEngine.prepareMessage(template,client)).isEqualTo(msg);
        assertThat(client.getEmail()).isEqualTo(email);
    }
    
    @Test
    public void testSpy(){
        client=spy(Client.class);
        template=spy(Template.class);
        mailServer=spy(MailServer.class);
        templateEngine=spy(TemplateEngine.class);
        messenger=new Messenger(mailServer,templateEngine);
        
        
        when(templateEngine.prepareMessage(template,client)).thenReturn(msg);
        when(client.getEmail()).thenReturn(email);
        
        messenger.sendMessage(client,template);
        
        verify(client).getEmail();
        verify(templateEngine).prepareMessage(template,client);
        
        assertThat(templateEngine.prepareMessage(template,client)).isEqualTo(msg);
        assertThat(client.getEmail()).isEqualTo(email);
    }
    
    
}
