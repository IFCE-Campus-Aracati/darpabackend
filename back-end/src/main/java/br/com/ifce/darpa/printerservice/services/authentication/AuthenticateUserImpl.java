package br.com.ifce.darpa.printerservice.services.authentication;

import br.com.ifce.darpa.printerservice.config.JwtService;
import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import br.com.ifce.darpa.printerservice.services.AuthenticateUser;
import br.com.ifce.darpa.printerservice.services.exceptions.ResourceNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserImpl implements AuthenticateUser {

    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticateUserImpl(UserRepository repository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public Response execute(Request request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        var user = repository.findByEmail(request.email())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Email não encontrado!")
                );

        var jwtToken = jwtService.generateToken(user);

        return new Response(
                user.getRole(),
                jwtToken,
                new UserDetails(
                        user.getFirstName(), user.getLastName(), user.getEmail()
                )
        );
    }
}
