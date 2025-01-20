package br.ufrn.imd.PotyCine.services;

import br.ufrn.imd.PotyCine.config.SecurityConfig;
import br.ufrn.imd.PotyCine.domain.Favorite;
import br.ufrn.imd.PotyCine.domain.Ticket;
import br.ufrn.imd.PotyCine.domain.User;
import br.ufrn.imd.PotyCine.dto.CreateUserDto;
import br.ufrn.imd.PotyCine.dto.LoginUserDto;
import br.ufrn.imd.PotyCine.dto.RecoveryJwtTokenDto;
import br.ufrn.imd.PotyCine.repositories.FavoriteRepository;
import br.ufrn.imd.PotyCine.repositories.TicketRepository;
import br.ufrn.imd.PotyCine.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;
    private final TicketRepository ticketRepository;
    private final FavoriteRepository favoriteRepository;

    public UserService(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService,
                       UserRepository userRepository, SecurityConfig securityConfig,
                       TicketRepository ticketRepository, FavoriteRepository favoriteRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
        this.ticketRepository = ticketRepository;
        this.favoriteRepository = favoriteRepository;
    }

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public User createUser(CreateUserDto createUserDto) {
        User newUser = User.builder()
                .name(createUserDto.name())
                .email(createUserDto.email())
                .password(securityConfig.passwordEncoder().encode(createUserDto.password()))
                .role(createUserDto.role())
                .build();

        return userRepository.save(newUser);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Ticket> getUserTickets(Long userId) {
        User user = findUserById(userId);
        return ticketRepository.findByUser(user);
    }

    public List<Favorite> getUserFavorites(Long userId) {
        User user = findUserById(userId);
        return favoriteRepository.findByUser(user);
    }
}
