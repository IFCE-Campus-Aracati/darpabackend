package br.com.ifce.darpa.printerservice.resources;

import br.com.ifce.darpa.printerservice.services.AuthenticateUser;
import br.com.ifce.darpa.printerservice.services.RegisterNewUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/auth")
public class AuthenticationResource {

    private final AuthenticateUser authenticateUser;
    private final RegisterNewUser registerNewUser;

    public AuthenticationResource(AuthenticateUser authenticateUser, RegisterNewUser registerNewUser) {
        this.authenticateUser = authenticateUser;
        this.registerNewUser = registerNewUser;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterNewUser.Response> register(
            @RequestBody RegisterNewUser.Request request
    ) {
        return ResponseEntity.ok(registerNewUser.execute(request));
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticateUser.Response> authenticate(
            @RequestBody AuthenticateUser.Request request
    ) {
        return ResponseEntity.ok(authenticateUser.execute(request));
    }
}
