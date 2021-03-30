package backcurso.springbootessentials.service;

import backcurso.springbootessentials.domain.Anime;
import backcurso.springbootessentials.exception.BadRequestException;
import backcurso.springbootessentials.repository.AnimeRepository;
import backcurso.springbootessentials.util.AnimeCreator;
import backcurso.springbootessentials.util.AnimePostDTOCreator;
import backcurso.springbootessentials.util.AnimePutDTOCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Anime Service")
public class AnimeServiceTest {

    @InjectMocks
    private AnimeService animeService;

    @Mock
    private AnimeRepository animeRepositoryMock;

    @BeforeEach
    void setup() {
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(animePage);
        BDDMockito.when(animeRepositoryMock.findAll()).thenReturn(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of((AnimeCreator.createValidAnime())));
        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString())).thenReturn(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeRepositoryMock.save(ArgumentMatchers.any(Anime.class))).thenReturn(AnimeCreator.createValidAnime());
        BDDMockito.doNothing().when(animeRepositoryMock).delete(ArgumentMatchers.any(Anime.class));

    }

    @Test
    void listAllWithPaginationtest() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        Page<Anime> animePage = animeService.listAll(PageRequest.of(1,1));

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    void listAlltest() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes = animeService.listAllNonPageable();

        Assertions.assertThat(animes).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findByIdTest() {
        Integer expectedId = AnimeCreator.createValidAnime().getId();
        Anime anime = animeService.findById(0);

        Assertions.assertThat(anime).isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    void findByIdExceptionTest() {
        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> animeService.findById(1));
    }

    @Test
    void findByNameTest() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes = animeService.findByName("anime");

        Assertions.assertThat(animes).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findByNameExceptionTest() {
        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString())).thenReturn(Collections.emptyList());

        String expectedName = AnimeCreator.createValidAnime().getName();

        List<Anime> animes = animeService.findByName("anime");

        Assertions.assertThat(animes).isNotNull().isEmpty();
    }

    @Test
    void saveTest() {
        Anime anime = animeService.save(AnimePostDTOCreator.createAnimePostDTO());

        Assertions.assertThat(anime).isNotNull().isEqualTo(AnimeCreator.createValidAnime());
    }

    @Test
    void updateTest() {
        Assertions.assertThatCode(() -> animeService.update(AnimePutDTOCreator.createAnimePutDTO())).doesNotThrowAnyException();
    }

    @Test
    void deleteTest() {
        Assertions.assertThatCode(() -> animeService.delete(1)).doesNotThrowAnyException();
    }
}
