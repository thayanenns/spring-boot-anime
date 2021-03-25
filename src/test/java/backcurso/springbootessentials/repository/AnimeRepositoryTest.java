package backcurso.springbootessentials.repository;

import backcurso.springbootessentials.domain.Anime;
import backcurso.springbootessentials.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    public void saveTest(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(anime);
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(anime.getName());
    }

    @Test
    public void saveUpdateTest(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(anime);
        animeSaved.setName("Naruto");

        Anime animeUpdated = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
    }


    @Test
    public void deleteTest(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(anime);

        this.animeRepository.delete(animeSaved);

        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());
        Assertions.assertThat(animeOptional.isEmpty());
    }

    @Test
    public void findByNameTest(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(anime);
        String name = animeSaved.getName();
        List<Anime> animes = this.animeRepository.findByName(name);

        Assertions.assertThat(animes).isNotEmpty().contains(animeSaved);
    }

    @Test
    public void findByNameEmptyListTest(){
       List<Anime> animes = this.animeRepository.findByName("test");
       Assertions.assertThat(animes).isEmpty();

    }
    @Test
    public void saveExceptionTest(){
        Anime anime = new Anime();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(() -> this.animeRepository.save(anime)).withMessageContaining("The anime name cannot be empty");
    }


    private Anime createAnime(){
        return Anime.builder().name("DBZ").build();
    }
}