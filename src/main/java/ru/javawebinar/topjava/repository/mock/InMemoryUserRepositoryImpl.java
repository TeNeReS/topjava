package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.NamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        this.save(new User(counter.incrementAndGet(), "Вася", "mailone@mail.com", "one", Role.ROLE_ADMIN));
        this.save(new User(counter.incrementAndGet(), "Петя", "mailtwo@mail.com", "two", Role.ROLE_USER));
        this.save(new User(counter.incrementAndGet(), "Коля", "mailthree@mail.com", "three", Role.ROLE_USER));
    }
    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        try {
            repository.remove(id);
        }
        catch (NullPointerException e){
            return false;
        }
        return true;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        User user;
        try {
            user = repository.get(id);
        }
        catch (NullPointerException e){
            return null;
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return repository.values().stream()
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        try {
            return repository.values().stream()
                    .filter(user -> user.getEmail().equals(email))
                    .collect(Collectors.toList()).get(0);
        }
        catch (NullPointerException e){
            return null;
        }
    }


}
