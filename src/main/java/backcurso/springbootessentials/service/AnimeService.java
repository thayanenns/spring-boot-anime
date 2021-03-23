package backcurso.springbootessentials.service;

import backcurso.springbootessentials.DTO.AnimePostDTO;
import backcurso.springbootessentials.DTO.AnimePutDTO;
import backcurso.springbootessentials.domain.Anime;
import backcurso.springbootessentials.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;
    private final AnimePostDTO animePostDTO;
    private final AnimePutDTO animePutDTO;

    public List<Anime> listAll(){
        return animeRepository.findAll();
    }

    public Anime findById(Integer id) {
        return animeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found"));
    }

    public Anime save(AnimePostDTO anime){
        return animeRepository.save(Anime.builder().name(animePostDTO.getName()).build());
    }

    public void delete(Integer id) {
        animeRepository.delete(findById(id));
    }

    public void update(AnimePutDTO animePutDTO) {
        Anime savedAnime = findById(animePutDTO.getId());
        Anime anime = Anime.builder()
                .id(savedAnime.getId())
                .name(animePutDTO.getName())
                .build();

        animeRepository.save(anime);
    }
}
