package com.sursindmitry.crud.service;

import com.sursindmitry.crud.dto.CreateDto;
import com.sursindmitry.crud.model.User;
import java.util.List;

public interface UserService {

    void create(CreateDto dto);

    List<User> getAll();

    User findUserById(Long id);

    void deleteUserById(Long userId);

    void update(CreateDto dto, Long id);

    User findUserByEmail(String email);
}
