package br.com.ifce.darpa.printerservice.resources;

import br.com.ifce.darpa.printerservice.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
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
    public ResponseEntity<Page<ListRegisteredUsers.Response>> findAll(Pageable pageable) {
        Page<ListRegisteredUsers.Response> list = listRegisteredUsers.execute(new ListRegisteredUsers.Request(pageable.getPageNumber(), pageable.getPageSize()));
        return ResponseEntity.ok().body(list);
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
