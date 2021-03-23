package backcurso.springbootessentials.repository;

import backcurso.springbootessentials.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Integer> {
}
