package com.sursindmitry.crud.repositry;

import com.sursindmitry.crud.model.User;
import jakarta.annotation.Nonnull;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Nonnull
    List<User> findAll();


    User findByEmail(String email);

}
