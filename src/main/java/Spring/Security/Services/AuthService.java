package Spring.Security.Services;

import Spring.Security.Dto.LoginDto;
import Spring.Security.Dto.SignupDto;
import Spring.Security.Dto.UserDto;
import Spring.Security.Entity.UserEntity;
import Spring.Security.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto signUp(SignupDto signupDto){

        UserEntity user = modelMapper.map(signupDto, UserEntity.class);

        if(userRepository.findByEmail(user.getEmail()).isPresent()){

            throw new BadCredentialsException("User with this email " + user.getEmail() + " already exists.");

        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return modelMapper.map(user, UserDto.class);

    }

    public boolean login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        return authentication.isAuthenticated();

    }
}
