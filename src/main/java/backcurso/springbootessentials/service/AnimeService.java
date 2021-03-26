package backcurso.springbootessentials.service;

import backcurso.springbootessentials.DTO.AnimePostDTO;
import backcurso.springbootessentials.DTO.AnimePutDTO;
import backcurso.springbootessentials.domain.Anime;
import backcurso.springbootessentials.exception.BadRequestException;
import backcurso.springbootessentials.mapper.AnimeMapper;
import backcurso.springbootessentials.repository.AnimeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public List<Anime> listAllNonPageable() {
        return animeRepository.findAll();
    }

    public Anime findById(Integer id) {
        return animeRepository.findById(id).orElseThrow(() -> new BadRequestException("Anime not Found"));
    }

    @Transactional
    public Anime save(AnimePostDTO animePostDTO){
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostDTO));
    }

    public void update(AnimePutDTO animePutDTO) {
        Anime savedAnime = findById(animePutDTO.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutDTO);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }

    public void delete(Integer id) {
        animeRepository.delete(findById(id));
    }

    public List<Anime> findByName(String name){
        return animeRepository.findByName(name);
    }

}
