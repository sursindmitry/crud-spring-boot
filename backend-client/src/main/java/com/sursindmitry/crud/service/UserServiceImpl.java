package com.sursindmitry.crud.service;


import com.sursindmitry.crud.dto.CreateDto;
import com.sursindmitry.crud.model.Role;
import com.sursindmitry.crud.model.User;
import com.sursindmitry.crud.repositry.RoleRepository;
import com.sursindmitry.crud.repositry.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void create(CreateDto dto) {

        Set<Role> roles = dto.getRoles().stream()
            .map(role -> roleRepository.findByName("ROLE_" + role.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Role not found: " + role)))
            .collect(Collectors.toSet());

        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(roles);

        userRepository.save(user);
    }

    @Transactional
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


    @Transactional
    @Override
    public User findUserById(Long id) {

        return userRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Transactional
    @Override
    public void deleteUserById(Long userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("ID cannot be less than zero or less");
        }

        userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));

        userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public void update(CreateDto dto, Long id) {
        if (id < 1) {
            throw new IllegalArgumentException("ID cannot be less than zero or less");
        }

        User findUser = userRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("User not found"));

        findUser.setName(dto.getName());
        findUser.setSurname(dto.getSurname());
        findUser.setEmail(dto.getEmail());
        findUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Role> roles = dto.getRoles().stream()
            .map(role -> roleRepository.findByName("ROLE_" + role.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Role not found: " + role)))
            .collect(Collectors.toSet());
        findUser.setRoles(roles);

        userRepository.save(findUser);
    }

    @Transactional
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
