package kd.dev;

import kd.dev.dao.VilleRepository;
import kd.dev.entities.Ville;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CinemaInitServiceImplTest {

    @MockBean
    private VilleRepository villeRepository;

    Ville ville;

    @Before
    public void setUp() {
        ville = new Ville();
        ville.setName("toto");
        ville.setAltitude(123456);
        ville.setLatitude(123456);
        ville.setLongitude(123456);
        ville.setId(Long.parseLong("123"));
        ville.setCinemas(null);
    }

    //saved method
    @Test
    public void testSaveVille(){
        when(villeRepository.save(ville)).thenReturn(ville);
        Ville savedVille = villeRepository.save(ville);
        assertEquals(savedVille, villeRepository.save(ville));
        assertThat(savedVille.getId()).isNotNull();
    }

    //find method
    @Test
    public void testFindVille(){
        when(villeRepository.findAll()).thenReturn(Stream.of(ville).collect(Collectors.toList()));
        assertEquals(1, villeRepository.findAll().size());
    }

    //delete method
    @Test
    public void testDeleteVille(){
        villeRepository.delete(ville);
        verify(villeRepository, times(1)).delete(ville);
    }


}
