import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FriendshipsMongoTest {

    @Mock
    private FriendsCollection friendsCollection;
    
    @InjectMocks
    private FriendshipsMongo friendshipsMongo;
    
    @Test
    public void mockingWorksAsExpected(){
        Person joe = new Person("Joe");
        when(friendsCollection.findByName("Joe")).thenReturn(joe);
        assertThat(friendsCollection.findByName("Joe")).isEqualTo(joe);
    }
    
    @Test
    public void alexDoesNotHaveFriends(){
        
        assertThat(friendshipsMongo.getFriendsList("Alex")).isEmpty();
    }

    @Test
    public void joeHas5Friends(){
        List<String> expected = Arrays.asList(new String[]{"Karol","Dawid","Maciej","Tomek","Adam"});
        Person joe = mock(Person.class);
        when(friendsCollection.findByName("Joe")).thenReturn(joe);
        when(joe.getFriends()).thenReturn(expected);
    
        
        assertThat(friendshipsMongo.getFriendsList("Joe")).hasSize(5).containsOnly("Karol","Dawid","Maciej","Tomek","Adam");
        verify(friendsCollection).findByName("Joe");
        verify(joe).getFriends();
    }

    //Dodane testy
    @Test
    public void joeIsFriendWithAlex(){
        Person joe=mock(Person.class);
        when(friendsCollection.findByName("Joe")).thenReturn(joe);
        when(joe.getFriends()).thenReturn(Arrays.asList("Alex"));
        
        
        assertThat(friendshipsMongo.areFriends("Joe","Alex")).isTrue();
        verify(friendsCollection).findByName("Joe");
        verify(joe).getFriends();
    }

    @Test
    public void joeIsNotFriendWithAlex(){
        Person joe=mock(Person.class);
        when(friendsCollection.findByName("Joe")).thenReturn(joe);
        when(joe.getFriends()).thenReturn(new ArrayList<>());
    
        
        assertThat(friendshipsMongo.areFriends("Joe","Alex")).isFalse();
        verify(friendsCollection).findByName("Joe");
        verify(joe).getFriends();
    }

    @Test
    public void getAlexName(){
        Person alex=mock(Person.class);
        when(alex.getName()).thenReturn("Alex");
        

        assertThat(alex.getName()).isEqualTo("Alex");
        verify(alex).getName();
    }

    @Test
    public void setNullPersonName(){
        Person alex=mock(Person.class);
        doThrow(new NullPointerException()).when(alex)
                .setName(null);
        assertThrows(NullPointerException.class, () -> alex.setName(null));
    }

    @Test
    public void addNullFriend(){
        Person alex=mock(Person.class);
        doThrow(new IllegalArgumentException()).when(alex)
                .setName("");
        assertThrows(IllegalArgumentException.class, () -> alex.setName(""));
    }


}
