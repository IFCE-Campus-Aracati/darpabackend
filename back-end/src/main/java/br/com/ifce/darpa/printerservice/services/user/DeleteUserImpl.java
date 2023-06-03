package br.com.ifce.darpa.printerservice.services.user;

import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import br.com.ifce.darpa.printerservice.services.DeleteUser;
import br.com.ifce.darpa.printerservice.services.exceptions.DatabaseException;
import br.com.ifce.darpa.printerservice.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserImpl implements DeleteUser {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void execute(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id não encontrado!" + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade de dados!");
        }
    }

}
