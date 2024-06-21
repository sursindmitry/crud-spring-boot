package com.sursindmitry.crud.service;


import com.sursindmitry.crud.model.User;
import com.sursindmitry.crud.repositry.UserRepository;
import com.sursindmitry.crud.dto.CreateDto;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudServiceImpl implements CrudService {

    private final UserRepository userRepository;

    public CrudServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void create(CreateDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());

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
    public void update(User user) {
        if (user.getId() < 1) {
            throw new IllegalArgumentException("ID cannot be less than zero or less");
        }

        userRepository.findById(user.getId())
            .orElseThrow(() -> new NoSuchElementException("User not found"));

        userRepository.save(user);
    }
}
