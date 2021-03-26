package backcurso.springbootessentials.util;


import backcurso.springbootessentials.DTO.AnimePutDTO;

public class AnimePutDTOCreator {
    public static AnimePutDTO createAnimePutDTO(){
        return AnimePutDTO.builder().id(AnimeCreator.createValidUpdatedAnime().getId()).name(AnimeCreator.createValidUpdatedAnime().getName()).build();
    }
}
