package br.com.ifce.darpa.printerservice.services.user;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.models.User;
import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import br.com.ifce.darpa.printerservice.services.SearchUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchUserImpl implements SearchUser {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response execute(Request request) {
        return userRepository
                .findByEmail(request.email())
                .map(this::userToResponse)
                .orElseThrow(() -> new NotFoundException(
                        "user with email %s not found".formatted(request.email())
                ));
    }

    private SearchUser.Response userToResponse(User user) {
        return new SearchUser.Response(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
