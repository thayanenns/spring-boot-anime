package backcurso.springbootessentials.integration;

import backcurso.springbootessentials.domain.Anime;
import backcurso.springbootessentials.repository.AnimeRepository;
import backcurso.springbootessentials.util.AnimeCreator;
import backcurso.springbootessentials.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

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

}
