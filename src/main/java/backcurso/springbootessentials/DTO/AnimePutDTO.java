package backcurso.springbootessentials.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimePutDTO {
    private Integer id;
    private String name;
}
