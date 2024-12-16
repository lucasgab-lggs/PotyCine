package br.ufrn.imd.PotyCine.controllers;

import br.ufrn.imd.PotyCine.dto.CreateUserDto;
import br.ufrn.imd.PotyCine.dto.LoginUserDto;
import br.ufrn.imd.PotyCine.dto.RecoveryJwtTokenDto;
import br.ufrn.imd.PotyCine.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Loga o usuário")
    @ApiResponse(responseCode = "200", description = "Loga usuário")
    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto){
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Operation(summary = "Cria um usuário")
    @ApiResponse(responseCode = "201", description = "Cria usuário")
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
