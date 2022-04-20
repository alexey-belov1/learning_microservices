package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    Optional<User> findDistinctByLogin(String name);
}
