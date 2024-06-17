package rs.ac.bg.fon.ai.naprednoProgramiranje.actor;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TestActorService {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorService actorService;

    Actor a;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
         a = new Actor();
    }
    @AfterEach
    public void tearDown() {
         a = null;
    }
    @Test
    public void TestSaveActorNull() {
        a = null;
        assertThrows(NullPointerException.class, () -> {
            actorService.saveActor(a);
        });
    }

    @Test
    public void TestSaveActorOK(){
       a.setNoOfOscars(3);
       when(actorRepository.save(a)).thenReturn(a);
       Actor savedActor = actorService.saveActor(a);
       assertEquals(a, savedActor);
       verify(actorRepository,times(1)).save(a); //a neat way to check that I called for
        //actor repo only once!
    }
}









