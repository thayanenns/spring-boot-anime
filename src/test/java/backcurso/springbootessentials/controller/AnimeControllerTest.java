package backcurso.springbootessentials.controller;

import backcurso.springbootessentials.domain.Anime;
import backcurso.springbootessentials.service.AnimeService;
import backcurso.springbootessentials.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class AnimeControllerTest {

    @InjectMocks
    public AnimeController animeController;

    @Mock
    private AnimeService animeServiceMock;

    @BeforeEach
    void setup(){
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any())).thenReturn(animePage);
    }
    @Test
    void listWithPagigationtest(){
        String expectedName = AnimeCreator.createValidAnime().getName();
        Page<Anime> animePage = animeController.list(null).getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);


    }
}
