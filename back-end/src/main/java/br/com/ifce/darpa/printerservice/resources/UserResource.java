package br.com.ifce.darpa.printerservice.resources;

import br.com.ifce.darpa.printerservice.services.DeleteUser;
import br.com.ifce.darpa.printerservice.services.ListRegisteredUsers;
import br.com.ifce.darpa.printerservice.services.SearchUser;
import br.com.ifce.darpa.printerservice.services.UpdateUser;
import br.com.ifce.darpa.printerservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserResource {

    @Autowired
    private UserService service;

    @Autowired
    private ListRegisteredUsers listRegisteredUsers;

    @Autowired
    private UpdateUser updateUser;

    @Autowired
    private SearchUser searchUser;

    @Autowired
    private DeleteUser deleteUser;

    @GetMapping
    public ResponseEntity<ListRegisteredUsers.Response> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        var users = listRegisteredUsers.execute(new ListRegisteredUsers.Request(page, size));
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<SearchUser.Response> search(@RequestBody SearchUser.Request email) {
        SearchUser.Response response = searchUser.execute(email);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UpdateUser.Response> update(@PathVariable Long id, @RequestBody UpdateUser.Request request) {
        UpdateUser.Response response = updateUser.execute(id, request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUser.execute(id);
        return ResponseEntity.noContent().build();
    }
}
