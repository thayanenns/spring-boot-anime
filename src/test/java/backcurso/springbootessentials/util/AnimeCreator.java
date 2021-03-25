package backcurso.springbootessentials.util;

import backcurso.springbootessentials.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder().name("DBZ").build();
    }
    public static Anime createValidAnime(){
        return Anime.builder().name("DBZ").id(1).build();
    }
    public static Anime createValidUpdatedAnime(){
        return Anime.builder().name("DBZ 2").id(1).build();
    }

}
