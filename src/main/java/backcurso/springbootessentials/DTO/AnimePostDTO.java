package backcurso.springbootessentials.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class AnimePostDTO {
    @NotEmpty(message = "The anime name cannot be empty")
    @NotBlank(message = "The anime name cannot be blank")
    private String name;
}
