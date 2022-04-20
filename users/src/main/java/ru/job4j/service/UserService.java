package ru.job4j.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.domain.User;
import ru.job4j.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByLogin(String login) {
        return this.userRepository.findDistinctByLogin(login);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public User save(User user) {

        if (user == null || user.getLogin() == null) {
            throw new RuntimeException();
        }

        if (this.userRepository.findDistinctByLogin(user.getLogin()).isPresent()) {
            throw new RuntimeException();
        }

        return this.userRepository.save(user);
    }
}
