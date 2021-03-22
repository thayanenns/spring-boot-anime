package backcurso.springbootessentials.repository;

import backcurso.springbootessentials.domain.Anime;

import java.util.List;

public interface AnimeRepository {
    List<Anime> listAll();
}
