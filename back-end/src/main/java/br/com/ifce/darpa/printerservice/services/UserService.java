package br.com.ifce.darpa.printerservice.services;

import br.com.ifce.darpa.printerservice.dtos.UserDTO;
import br.com.ifce.darpa.printerservice.dtos.UserInsertDTO;
import br.com.ifce.darpa.printerservice.dtos.UserUpdateDTO;
import br.com.ifce.darpa.printerservice.models.User;
import br.com.ifce.darpa.printerservice.repositories.RoleRepository;
import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import br.com.ifce.darpa.printerservice.services.exceptions.DatabaseException;
import br.com.ifce.darpa.printerservice.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;


    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> list = repository.findAll(pageable);
        return list.map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado!"));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        copyInsertDTOToEntity(dto, entity);
        entity.setPassword(bCryptPasswordEncoder.encode(dto.password()));
        entity = repository.save(entity);
        return new UserDTO(entity);
    }


    @Transactional
    public UserDTO update(Long id, UserUpdateDTO dto) {
        try {
            User entity = repository.getReferenceById(id);
            copyUpdateDTOToEntity(dto, entity);
            entity = repository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado!" + id);
        }
    }



    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id não encontrado!" + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade de dados!");
        }
    }


    private void copyUpdateDTOToEntity(UserUpdateDTO dto, User entity) {
        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.getRoles().clear();
        dto.roles().forEach(roleDTO -> entity.getRoles().add(roleRepository.getReferenceById(roleDTO.getId())));
    }

    private void copyInsertDTOToEntity(UserInsertDTO dto, User entity) {
        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.getRoles().clear();
        dto.roles().forEach(roleDTO -> entity.getRoles().add(roleRepository.getReferenceById(roleDTO.getId())));
    }


}
