package backcurso.springbootessentials.resource;

import backcurso.springbootessentials.domain.Anime;
import backcurso.springbootessentials.service.AnimeService;
import backcurso.springbootessentials.util.DateUtil;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/animes")
@RequiredArgsConstructor
public class AnimeResource {
    private final AnimeService animeService;

    @GetMapping
    public List<Anime> List(){
        return animeService.listAll();
    }
}
