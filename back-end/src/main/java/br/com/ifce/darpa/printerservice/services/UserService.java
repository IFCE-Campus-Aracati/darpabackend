package br.com.ifce.darpa.printerservice.services;

import br.com.ifce.darpa.printerservice.models.User;
import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository repository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> obj = repository.findByEmail(username);
        if (obj.isPresent()) {
            logger.info("User: " + username + " found!");
            return obj.get();
        }
        logger.error("User: " + username + " not found!");
        throw new UsernameNotFoundException("Email não encontrado!");

    }
}
