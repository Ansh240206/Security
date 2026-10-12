package Spring.Security.Dto;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class UserDto {

    private String email;

    private String name;

    private Long id;

}
