package backcurso.springbootessentials.service;

import backcurso.springbootessentials.domain.Anime;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnimeService {

    public List<Anime> listAll(){
        return List.of(new Anime(1,"DBZ"), new Anime(2,"Naruto"));

    }
}
