package backcurso.springbootessentials.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimePostDTO {
    @NotEmpty(message = "The anime name cannot be empty")
    @NotBlank(message = "The anime name cannot be blank")
    private String name;
}
