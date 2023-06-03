package br.com.ifce.darpa.printerservice.services.user;

import br.com.ifce.darpa.printerservice.models.User;
import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import br.com.ifce.darpa.printerservice.services.ListRegisteredUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ListRegisteredUsersImpl implements ListRegisteredUsers {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<ListRegisteredUsers.Response> execute(ListRegisteredUsers.Request request) {
        return userRepository
                .findAll(PageRequest.of(request.pageNumber(), request.pageSize()))
                .map(this::userToResponse);
    }

    private ListRegisteredUsers.Response userToResponse(User user) {
        return new ListRegisteredUsers.Response(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
