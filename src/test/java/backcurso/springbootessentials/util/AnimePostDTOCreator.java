package backcurso.springbootessentials.util;


import backcurso.springbootessentials.DTO.AnimePostDTO;

public class AnimePostDTOCreator {
    public static AnimePostDTO createAnimePostDTO(){
        return AnimePostDTO.builder().name(AnimeCreator.createAnimeToBeSaved().getName()).build();
    }
}
