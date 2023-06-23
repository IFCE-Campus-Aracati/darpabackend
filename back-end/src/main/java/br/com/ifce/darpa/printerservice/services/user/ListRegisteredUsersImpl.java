package br.com.ifce.darpa.printerservice.services.user;

import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import br.com.ifce.darpa.printerservice.services.ListRegisteredUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ListRegisteredUsersImpl implements ListRegisteredUsers {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ListRegisteredUsers.Response execute(ListRegisteredUsers.Request request) {
        var pageOfUsers = userRepository
                .findAll(PageRequest.of(request.pageNumber(), request.pageSize()))
                .map(user -> new UserDetails(
                        user.getId(),
                        user.getFirstName() + " " + user.getLastName(),
                        user.getEmail(),
                        user.getRole()
                ));

        return new Response(
                pageOfUsers.getTotalElements(),
                pageOfUsers.getContent(),
                pageOfUsers.getTotalPages(),
                pageOfUsers.getNumber()
        );
    }
}
