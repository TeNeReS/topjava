package ru.javawebinar.topjava.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    @Transactional(timeout = 10)
    default User getWithMeals(int id) {
        User user = get(id);
        user.getMeals().stream().findFirst();
        return user;
    }

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}