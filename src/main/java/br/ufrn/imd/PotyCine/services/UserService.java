package br.ufrn.imd.PotyCine.services;

import br.ufrn.imd.PotyCine.config.SecurityConfig;
import br.ufrn.imd.PotyCine.domain.User;
import br.ufrn.imd.PotyCine.dto.CreateUserDto;
import br.ufrn.imd.PotyCine.dto.LoginUserDto;
import br.ufrn.imd.PotyCine.dto.RecoveryJwtTokenDto;
import br.ufrn.imd.PotyCine.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;

    public UserService(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, UserRepository userRepository, SecurityConfig securityConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public void createUser(CreateUserDto createUserDto) {
        User newUser = User.builder()
                .name(createUserDto.name())
                .email(createUserDto.email())
                .password(securityConfig.passwordEncoder().encode(createUserDto.password()))
                .role(createUserDto.role())
                .build();

        userRepository.save(newUser);
    }
}
