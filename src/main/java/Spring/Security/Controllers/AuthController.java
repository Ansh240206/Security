package Spring.Security.Controllers;

import Spring.Security.Dto.LoginDto;
import Spring.Security.Dto.SignupDto;
import Spring.Security.Dto.UserDto;
import Spring.Security.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto){

        UserDto userDto = authService.signUp(signupDto);

        return ResponseEntity.ok(userDto);

    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDto loginDto){

        Boolean isAuthenticated = authService.login(loginDto);

        return ResponseEntity.ok(isAuthenticated);

    }

}
