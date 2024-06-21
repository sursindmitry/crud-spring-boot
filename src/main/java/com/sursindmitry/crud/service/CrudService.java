package com.sursindmitry.crud.service;

import com.sursindmitry.crud.model.User;
import com.sursindmitry.crud.dto.CreateDto;
import java.util.List;

public interface CrudService {

    void create(CreateDto dto);

    List<User> getAll();

    User findUserById(Long id);

    void deleteUserById(Long userId);

    void update(User user);
}
