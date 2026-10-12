package Spring.Security.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SignupDto {

    private String email;

    private String name;

    private String password;

}
