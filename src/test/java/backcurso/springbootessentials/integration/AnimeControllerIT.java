package backcurso.springbootessentials.integration;

import backcurso.springbootessentials.domain.Anime;
import backcurso.springbootessentials.repository.AnimeRepository;
import backcurso.springbootessentials.util.AnimeCreator;
import backcurso.springbootessentials.util.AnimePostDTOCreator;
import backcurso.springbootessentials.util.AnimePutDTOCreator;
import backcurso.springbootessentials.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class AnimeControllerIT {
    @Autowired
    public TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    void listWithPagigationtest() {
        Anime savedAnime = animeRepository.save((AnimeCreator.createAnimeToBeSaved()));
        String expectedName = savedAnime.getName();

        PageableResponse<Anime> animePage = testRestTemplate.exchange("/api/animes", HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<Anime>>(){}).getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void listAlltest() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes =  testRestTemplate.exchange("/api/animes", HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<Anime>>(){}).getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }
    }

    @Test
    void findByIdTest() {
        Integer expectedId = AnimeCreator.createValidAnime().getId();
        Anime anime = animeController.findById(0).getBody();

        Assertions.assertThat(anime).isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    void findByNameTest() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes = animeController.findByName("anime").getBody();

        Assertions.assertThat(animes).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findByNameExceptionTest() {
        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString())).thenReturn(Collections.emptyList());

        String expectedName = AnimeCreator.createValidAnime().getName();

        List<Anime> animes = animeController.findByName("anime").getBody();

        Assertions.assertThat(animes).isNotNull().isEmpty();
    }

    @Test
    void saveTest() {
        Anime anime = animeController.save(AnimePostDTOCreator.createAnimePostDTO()).getBody();

        Assertions.assertThat(anime).isNotNull().isEqualTo(AnimeCreator.createValidAnime());
    }

    @Test
    void updateTest() {
        Assertions.assertThatCode(() -> animeController.update(AnimePutDTOCreator.createAnimePutDTO())).doesNotThrowAnyException();
        ResponseEntity<Void> entity = animeController.update(AnimePutDTOCreator.createAnimePutDTO());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteTest() {
        Assertions.assertThatCode(() -> animeController.delete(1)).doesNotThrowAnyException();

        ResponseEntity<Void> entity = animeController.delete(1);

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
